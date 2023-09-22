package com.kyonggi.Capstone_Develop.repository;

import com.kyonggi.Capstone_Develop.config.JpaAuditingConfig;
import com.kyonggi.Capstone_Develop.domain.graduation.*;
import com.kyonggi.Capstone_Develop.domain.student.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@Import(JpaAuditingConfig.class)
@Transactional
class ApplyRepositoryTest {
    @Autowired
    private ApplyRepository applyRepository;
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private GraduationRepository graduationRepository;
    
    private Student student;
    
    private Student student2;
    
    private Graduation graduation;
    
    private Apply apply;
    
    // TODO: merge 후 학생 도메인 수정
    @BeforeEach
    void setUp() {
        student = new Student(
                "201812709",
                "dummyPassword",
                LocalDate.of(2023, 07, 18),
                "컴퓨터공학부",
                PhoneNumber.from("010-1111-1111"),
                Sex.MALE,
                "한상범",
                Email.from("1@naver.com"),
                RoleType.STUDENT,
                "answerPW",
                Classification.from("UNDERGRADUATE_STUDENT")
        );
    
        student2 = new Student(
                "201812709",
                "dummyPassword",
                LocalDate.of(2023, 07, 18),
                "컴퓨터공학부",
                PhoneNumber.from("010-1111-1111"),
                Sex.MALE,
                "한상범",
                Email.from("1@naver.com"),
                RoleType.STUDENT,
                "answerPW",
                Classification.from("UNDERGRADUATE_STUDENT")
        );
                
        graduation = new Graduation(
                Method.THESIS,
                Status.UNAPPROVAL,
                Step.RECEIVED,
                true,
                LocalDate.MAX,
                "김교수님"
        );
        studentRepository.save(student);
        studentRepository.save(student2);
        graduationRepository.save(graduation);
        apply = new Apply(student, graduation);
    }
    
    @Test
    @DisplayName("졸업 신청을 매핑 테이블에 저장하고 id로 찾는다.")
    void saveApplyAndFindById() {
        // given
        Long applyId = applyRepository.save(apply)
                .getId();
        
        // when
        Apply findApply = applyRepository.findById(applyId)
                .orElseThrow();
        Student findStudent = findApply.getStudent();
        Graduation findGraduation = findApply.getGraduation();
    
        // then
        assertAll(
                () -> assertThat(findApply.getId()).isNotNull(),
                () -> assertThat(findApply).isEqualTo(apply),
                () -> assertThat(findStudent.getId()).isEqualTo(student.getId()),
                () -> assertThat(findGraduation.getId()).isEqualTo(graduation.getId())
        );
    }
    
    @Test
    @DisplayName("학생 id로 졸업 신청 전체를 찾는다.")
    void findAllByStudentId() {
        // given
        Apply apply1 = createApply();
        applyRepository.save(apply1);
    
        Apply apply2 = createApply();
        applyRepository.save(apply2);
    
        // when
        List<Apply> findApplies = applyRepository.findAllByStudentId(student2.getId());
    
        // then
        assertAll(
                () -> assertThat(findApplies).hasSize(2),
                () -> assertThat(findApplies).extracting("id")
                        .containsExactly(apply1.getId(), apply2.getId())
        );
    }
    
    @Test
    @DisplayName("졸업자 신청을 매핑 테이블에서 삭제한다.")
    void deleteById() {
        // given
        Long applyId = applyRepository.save(apply)
                .getId();
    
        // when
        applyRepository.deleteById(applyId);
      
        // then
        assertThat(applyRepository.findById(applyId))
                .isEmpty();
    }
    
    private Apply createApply() {
        return new Apply(student2, graduation);
    }
}