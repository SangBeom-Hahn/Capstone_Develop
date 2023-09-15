package com.kyonggi.Capstone_Develop.repository;

import com.kyonggi.Capstone_Develop.config.JpaAuditingConfig;
import com.kyonggi.Capstone_Develop.domain.graduation.Graduation;
import com.kyonggi.Capstone_Develop.domain.graduation.Method;
import com.kyonggi.Capstone_Develop.domain.graduation.Status;
import com.kyonggi.Capstone_Develop.domain.graduation.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@Transactional
@Import(JpaAuditingConfig.class)
class GraduationRepositoryTest {
    @Autowired
    private GraduationRepository graduationRepository;
    
    private Graduation graduation;
    
    @BeforeEach
    void setUp() {
        graduation = new Graduation(
                Method.THESIS,
                Status.UNAPPROVAL,
                Step.RECEIVED,
                true,
                LocalDate.MAX,
                "김교수님"
        );
    }
    
    @Test
    @DisplayName("졸업자를 저장한다.")
    void save() {
        // given
        Graduation saveGraduation = graduationRepository.save(graduation);
      
        // then
        assertAll(
                () -> assertThat(saveGraduation.getId()).isNotNull(),
                () -> assertThat(saveGraduation).isEqualTo(graduation)
        );
    }
    
    @Test
    @DisplayName("졸업자를 id로 찾는다.")
    void findById() {
        // given
        Long graduationId = graduationRepository.save(graduation)
                .getId();
    
        // when
        Graduation actualGraduation = graduationRepository.findById(graduationId)
                .orElseThrow();

        // then
        assertThat(actualGraduation).extracting("id", "method", "status", "step", "capstoneCompletion", "graduationDate", "professorName")
                .containsExactly(
                        graduationId,
                        Method.THESIS,
                        Status.UNAPPROVAL,
                        Step.RECEIVED,
                        true,
                        LocalDate.MAX,
                        "김교수님"
                );
    }
    
    @Test
    @DisplayName("졸업자를 삭제한다.")
    void deleteGraduation() {
        // given
        Long graduationId = graduationRepository.save(graduation)
                .getId();
    
        // when
        graduationRepository.deleteById(graduationId);
      
        // then
        assertThat(graduationRepository.findById(graduationId)).isEmpty();
    }
}