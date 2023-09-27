package com.kyonggi.Capstone_Develop.service;

import com.kyonggi.Capstone_Develop.domain.graduation.*;
import com.kyonggi.Capstone_Develop.domain.student.*;
import com.kyonggi.Capstone_Develop.service.dto.situation.SituationResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class SituationServiceTest extends ServiceTest{
    private Student student;
    
    private Graduation graduation;
    
    private Apply apply1;
    
    private Apply apply2;
    
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
        
        graduation = new Graduation(
                Method.THESIS,
                Status.UNAPPROVAL,
                Step.RECEIVED,
                true,
                LocalDate.MAX,
                "김교수님"
        );
        
        studentRepository.save(student);
        graduationRepository.save(graduation);
        apply1 = new Apply(student, graduation);
        apply2 = new Apply(student, graduation);
        applyRepository.save(apply1);
        applyRepository.save(apply2);
    }
    
    @Test
    @DisplayName("학생 id로 졸업을 신청한 학생의 정보를 조회한다.")
    void findSituation() {
        // when
        SituationResponseDto situationResponseDto = situationService.findSituation(student.getId());
    
        // then
        assertThat(situationResponseDto).extracting("loginId", "name", "department", "professorName",
                        "graduationDate", "capstoneCompletion", "status", "step")
                .containsExactly(
                        student.getLoginId(),
                        student.getName(),
                        student.getDepartment(),
                        graduation.getProfessorName(),
                        graduation.getGraduationDate(),
                        graduation.getCapstoneCompletion(),
                        graduation.getStatus(),
                        graduation.getStep()
                );
    }
    
    @Test
    @DisplayName("학생 id로 졸업을 신청한 학생의 신청 id를 전부 조회한다.")
    void findApplyIds() {
        // given
        List<Long> actual = situationService.findSituation(student.getId())
                .getApplyIds();
    
        // when
        List<Long> expected = List.of(13L, 14L);
      
        // then
        assertAll(
                () -> assertThat(actual).hasSize(2),
                () -> assertThat(actual).isEqualTo(expected)
        );
    }
}