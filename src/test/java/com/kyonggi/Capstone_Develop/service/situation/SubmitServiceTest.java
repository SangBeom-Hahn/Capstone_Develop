package com.kyonggi.Capstone_Develop.service.situation;

import com.kyonggi.Capstone_Develop.controller.dto.situation.submit.SubmitSaveRequest;
import com.kyonggi.Capstone_Develop.domain.graduation.*;
import com.kyonggi.Capstone_Develop.domain.student.*;
import com.kyonggi.Capstone_Develop.exception.DuplicateSubmitException;
import com.kyonggi.Capstone_Develop.service.ServiceTest;
import com.kyonggi.Capstone_Develop.service.dto.situation.form.submit.SubmitResponseDto;
import com.kyonggi.Capstone_Develop.service.dto.situation.form.submit.SubmitSaveResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class SubmitServiceTest extends ServiceTest {
    private Student student;
    
    private Graduation graduation;
    
    private Apply apply;
    
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
        apply = new Apply(student, graduation);
        applyRepository.save(apply);
    }
    
    @Test
    @DisplayName("중복 신청을 하면 예외가 발생한다.")
    void throwException_DuplicateSubmit() {
        // given
        SubmitSaveRequest submitSaveRequest1 = createSubmitSaveRequest();
        SubmitSaveRequest submitSaveRequest2 = createSubmitSaveRequest();
    
        // when
        submitService.save(submitSaveRequest1.toServiceDto(), apply.getId());
      
        // then
        assertThatThrownBy(() -> submitService.save(submitSaveRequest2.toServiceDto(), apply.getId()))
                .isInstanceOf(DuplicateSubmitException.class)
                .hasMessage("이미 신청 접수 되었습니다. loginId={%s}", student.getLoginId());
    }
    
    @Test
    @DisplayName("신청 접수 폼을 저장하고 id로 조회한다.")
    void saveSubmitAndFind() {
        // given
        SubmitSaveRequest submitSaveRequest = createSubmitSaveRequest();
        Long submitId = submitService.save(submitSaveRequest.toServiceDto(), student.getId())
                .getId();
    
        // when
        SubmitResponseDto submitResponseDto = submitService.findSubmit(apply.getId());
    
        // then
        assertThat(submitResponseDto).extracting("id", "professorName", "graduationDate", "capstoneCompletion")
                .containsExactly(
                        submitId,
                        submitSaveRequest.getProfessorName(),
                        submitSaveRequest.getGraduationDate(),
                        submitSaveRequest.getCapstoneCompletion()
                );
    }
    
    private SubmitSaveRequest createSubmitSaveRequest() {
        return new SubmitSaveRequest(
                "김교수님",
                LocalDate.MAX,
                true
        );
    }
}