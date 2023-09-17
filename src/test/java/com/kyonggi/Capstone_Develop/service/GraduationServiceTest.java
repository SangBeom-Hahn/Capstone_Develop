package com.kyonggi.Capstone_Develop.service;

import com.kyonggi.Capstone_Develop.controller.dto.graduation.GraduationSaveRequest;
import com.kyonggi.Capstone_Develop.domain.graduation.*;
import com.kyonggi.Capstone_Develop.domain.student.*;
import com.kyonggi.Capstone_Develop.exception.DuplicateApplyException;
import com.kyonggi.Capstone_Develop.repository.StudentRepository;
import com.kyonggi.Capstone_Develop.service.dto.graduation.GraduationResponseDto;
import com.kyonggi.Capstone_Develop.service.dto.graduation.GraduationsResponseDto;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.kyonggi.Capstone_Develop.domain.graduation.Method.THESIS;
import static com.kyonggi.Capstone_Develop.domain.graduation.Status.APPROVAL;
import static com.kyonggi.Capstone_Develop.domain.graduation.Step.PROPOSAL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@Transactional
@RequiredArgsConstructor
@SpringBootTest
class GraduationServiceTest {
    @Autowired
    private GraduationService graduationService;
    
    @Autowired
    private StudentRepository studentRepository;
    
    private Student student1;
    
    private Student student2;
    
    @BeforeEach
    void setUp() {
        student1 = new Student(
                "cherry",
                "123#a",
                LocalDate.of(2023, 07, 18),
                "컴퓨터공학부",
                Grade.FOURTH,
                PhoneNumber.from("010-1111-1111"),
                Sex.FEMALE,
                "한상범",
                Email.from("1@naver.com"),
                "20182222"
                ,RoleType.STUDENT
        );
    
        student2 = new Student(
                "cherry",
                "123#a",
                LocalDate.of(2023, 07, 18),
                "컴퓨터공학부",
                Grade.FOURTH,
                PhoneNumber.from("010-1111-1111"),
                Sex.FEMALE,
                "한상범",
                Email.from("1@naver.com"),
                "20182222"
                ,RoleType.STUDENT
        );
        
        studentRepository.save(student1);
        studentRepository.save(student2);
    }

    @Test
    @DisplayName("졸업을 저장하고 전체 조회한다.")
    void saveGraduationAndFindAll() {
        // given
        GraduationSaveRequest graduationSaveRequest1 = createSaveRequest();
        GraduationSaveRequest graduationSaveRequest2 = createSaveRequest();
    
        // when
        graduationService.save(graduationSaveRequest1.toServiceDto(), student1.getId());
        graduationService.save(graduationSaveRequest2.toServiceDto(), student2.getId());
        GraduationsResponseDto actual =
                graduationService.findAllGraduation();
    
        // then
        assertAll(
                () -> assertThat(actual.getGraduationResponseDtos()).hasSize(2),
                () -> assertThat(actual.getGraduationResponseDtos()).extracting("method")
                        .containsExactly(THESIS, THESIS),
                () -> assertThat(actual.getGraduationResponseDtos()).extracting("status")
                        .containsExactly(APPROVAL, APPROVAL),
                () -> assertThat(actual.getGraduationResponseDtos()).extracting("step")
                        .containsExactly(PROPOSAL, PROPOSAL)
        );
    }
    
    @Test
    @DisplayName("같은 학생이 졸업을 중복 신청하면 예외가 발생한다.")
    void throwException_DuplicateApply() {
        // given
        GraduationSaveRequest graduationSaveRequest1 = createSaveRequest();
        GraduationSaveRequest graduationSaveRequest2 = createSaveRequest();
    
        // when
        graduationService.save(graduationSaveRequest1.toServiceDto(), student1.getId());
      
        // then
        assertThatThrownBy(() -> graduationService.save(graduationSaveRequest2.toServiceDto(), student1.getId()))
                .isInstanceOf(DuplicateApplyException.class)
                .hasMessage("동일 단계의 졸업 신청입니다. loginId={%s}", student1.getLoginId());
    }
    
    private static GraduationSaveRequest createSaveRequest() {
        return new GraduationSaveRequest("논문", "APPROVAL", "PROPOSAL");
    }
}