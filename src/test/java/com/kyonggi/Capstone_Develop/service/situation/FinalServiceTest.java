package com.kyonggi.Capstone_Develop.service.situation;

import com.kyonggi.Capstone_Develop.controller.dto.situation.finalreport.FinalSaveRequest;
import com.kyonggi.Capstone_Develop.domain.graduation.*;
import com.kyonggi.Capstone_Develop.domain.situation.Final;
import com.kyonggi.Capstone_Develop.domain.student.*;
import com.kyonggi.Capstone_Develop.exception.DuplicateFinalException;
import com.kyonggi.Capstone_Develop.exception.InvalidStepException;
import com.kyonggi.Capstone_Develop.service.ServiceTest;
import com.kyonggi.Capstone_Develop.service.dto.situation.form.finalreport.FinalResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.kyonggi.Capstone_Develop.domain.graduation.Step.FINAL_PASS;
import static com.kyonggi.Capstone_Develop.domain.graduation.Step.FINAL_REPORT;
import static com.kyonggi.Capstone_Develop.domain.situation.Approval.APPROVAL;
import static com.kyonggi.Capstone_Develop.domain.situation.Approval.REJECT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class FinalServiceTest extends ServiceTest {
    private Student student;
    
    private Student student2;
    
    private Graduation graduation;
    
    private Graduation graduation2;
    
    private Apply apply;
    
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
                FINAL_REPORT,
                true,
                LocalDate.MAX,
                "김교수님"
        );
        
        graduation2 = new Graduation(
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
        graduationRepository.save(graduation2);
        
        apply = new Apply(student, graduation);
        apply2 = new Apply(student2, graduation2);
        applyRepository.save(apply);
        applyRepository.save(apply2);
    }
    
    @Test
    @DisplayName("중복 신청을 하면 예외가 발생한다.")
    void throwException_DuplicateFinal() {
        // given
        FinalSaveRequest finalSaveRequest1 = createFinalSaveRequest();
        FinalSaveRequest finalSaveRequest2 = createFinalSaveRequest();
    
        // when
        finalService.saveFinal(finalSaveRequest1.toServiceDto(), student.getId());
        
        // then
        assertThatThrownBy(() -> finalService.saveFinal(finalSaveRequest2.toServiceDto(), student.getId()))
                .isInstanceOf(DuplicateFinalException.class)
                .hasMessage("이미 최종보고서 제출 되었습니다. loginId={%s}", student.getLoginId());
    }
    
    @Test
    @DisplayName("최종보고서 단계가 아닌 학생은 예외가 발생한다.")
    void throwException_invalidStep() {
        // given
        FinalSaveRequest finalSaveRequest = createFinalSaveRequest();
    
        // then
        assertThatThrownBy(() -> finalService.saveFinal(finalSaveRequest.toServiceDto(), student2.getId()))
                .isInstanceOf(InvalidStepException.class)
                .hasMessage("올바르지 않은 단계의 신청입니다. loginId={%s}", student2.getLoginId());
    }
    
    @Test
    @DisplayName("최종 보고서 폼을 저장하고 id로 조회한다.")
    void saveFinalAndFind() {
        // given
        FinalSaveRequest finalSaveRequest = createFinalSaveRequest();
        Long finalId = finalService.saveFinal(finalSaveRequest.toServiceDto(), student.getId())
                .getId();
        
        // when
        FinalResponseDto finalResponseDto = finalService.findFinal(apply.getId());
    
        // then
        assertThat(finalResponseDto).extracting("proposalId", "applyId", "title", "division", "qualification",
                        "pageNumber", "rejectReason")
                .containsExactly(
                        finalId,
                        apply.getId(),
                        finalSaveRequest.getTitle(),
                        finalSaveRequest.getDivision(),
                        finalSaveRequest.getQualification(),
                        finalSaveRequest.getPageNumber(),
                        null
                );
    }
    
    @Test
    @DisplayName("최종보고서 폼을 승인한다.")
    void approveFinal() {
        // given
        FinalSaveRequest finalSaveRequest = createFinalSaveRequest();
        Long finalId = finalService.saveFinal(finalSaveRequest.toServiceDto(), student.getId())
                .getId();
        
        // when
        finalService.approveFinal(apply.getId());
        Final finalReport = finalRepository.findById(finalId)
                .orElseThrow();
    
        // then
        assertAll(
                () -> assertThat(finalReport.getApproval()).isEqualTo(APPROVAL),
                () -> assertThat(graduation.getStep()).isEqualTo(FINAL_PASS),
        () -> assertThat(graduation.getStatus()).isEqualTo(Status.APPROVAL)
        );
    }
    
    @Test
    @DisplayName("최종보고서 폼을 반려한다.")
    void rejectFinal() {
        // given
        FinalSaveRequest finalSaveRequest = createFinalSaveRequest();
        Long finalId = finalService.saveFinal(finalSaveRequest.toServiceDto(), student.getId())
                .getId();
        String expectedRejectReason = "expectedRejectReason";
        
        // when
        finalService.rejectFinal(apply.getId(), expectedRejectReason);
        Final finalReport = finalRepository.findById(finalId)
                .orElseThrow();
        
        // then
        assertAll(
                () -> assertThat(finalReport.getApproval()).isEqualTo(REJECT),
                () -> assertThat(graduation.getStep()).isEqualTo(FINAL_REPORT),
                () -> assertThat(graduation.getStatus()).isEqualTo(Status.REJECT),
                () -> assertThat(finalReport.getRejectReason()).isEqualTo(expectedRejectReason)
        );
    }
    
    private FinalSaveRequest createFinalSaveRequest() {
        return new FinalSaveRequest(
                "title",
                "division",
                "qualification",
                20
        );
    }
}