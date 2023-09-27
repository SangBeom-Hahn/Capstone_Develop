package com.kyonggi.Capstone_Develop.service.situation;

import com.kyonggi.Capstone_Develop.controller.dto.situation.interim.InterimSaveRequest;
import com.kyonggi.Capstone_Develop.controller.dto.situation.proposal.ProposalSaveRequest;
import com.kyonggi.Capstone_Develop.domain.graduation.*;
import com.kyonggi.Capstone_Develop.domain.situation.Interim;
import com.kyonggi.Capstone_Develop.domain.situation.Proposal;
import com.kyonggi.Capstone_Develop.domain.student.*;
import com.kyonggi.Capstone_Develop.exception.DuplicateInterimException;
import com.kyonggi.Capstone_Develop.exception.DuplicateProposalException;
import com.kyonggi.Capstone_Develop.exception.InvalidStepException;
import com.kyonggi.Capstone_Develop.service.ServiceTest;
import com.kyonggi.Capstone_Develop.service.dto.situation.form.interim.InterimResponseDto;
import com.kyonggi.Capstone_Develop.service.dto.situation.form.proposal.ProposalResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.kyonggi.Capstone_Develop.domain.graduation.Step.*;
import static com.kyonggi.Capstone_Develop.domain.situation.Approval.APPROVAL;
import static com.kyonggi.Capstone_Develop.domain.situation.Approval.REJECT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class InterimServiceTest extends ServiceTest {
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
                INTERIM_REPORT,
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
    void throwException_DuplicateInterim() {
        // given
        InterimSaveRequest interimSaveRequest1 = createInterimSaveRequest();
        InterimSaveRequest interimSaveRequest2 = createInterimSaveRequest();
        
        // when
        interimService.saveInterim(interimSaveRequest1.toServiceDto(), student.getId());
        
        // then
        assertThatThrownBy(() -> interimService.saveInterim(interimSaveRequest2.toServiceDto(), student.getId()))
                .isInstanceOf(DuplicateInterimException.class)
                .hasMessage("이미 중간보고서 제출 되었습니다. loginId={%s}", student.getLoginId());
    }
    
    @Test
    @DisplayName("중간 보고서 단계가 아닌 학생은 예외가 발생한다.")
    void throwException_invalidStep() {
        // given
        InterimSaveRequest interimSaveRequest = createInterimSaveRequest();
    
        // then
        assertThatThrownBy(() -> interimService.saveInterim(interimSaveRequest.toServiceDto(), student2.getId()))
                .isInstanceOf(InvalidStepException.class)
                .hasMessage("올바르지 않은 단계의 신청입니다. loginId={%s}", student2.getLoginId());
    }
    
    @Test
    @DisplayName("중간 보고서 폼을 저장하고 id로 조회한다.")
    void saveProposalAndFind() {
        // given
        InterimSaveRequest interimSaveRequest = createInterimSaveRequest();
        Long interimId = interimService.saveInterim(interimSaveRequest.toServiceDto(), student.getId())
                .getId();
        
        // when
        InterimResponseDto interimResponseDto = interimService.findInterim(apply.getId());
    
        // then
        assertThat(interimResponseDto).extracting("interimId", "applyId", "title", "division", "text",
                        "plan", "rejectReason")
                .containsExactly(
                        interimId,
                        apply.getId(),
                        interimSaveRequest.getTitle(),
                        interimSaveRequest.getDivision(),
                        interimSaveRequest.getText(),
                        interimSaveRequest.getPlan(),
                        null
                );
    }
    
    @Test
    @DisplayName("최종 보고서 폼을 승인한다.")
    void approveProposal() {
        // given
        InterimSaveRequest interimSaveRequest = createInterimSaveRequest();
        Long interimId = interimService.saveInterim(interimSaveRequest.toServiceDto(), student.getId())
                .getId();
    
        // when
        interimService.approveInterim(apply.getId());
        Interim interim = interimRepository.findById(interimId)
                .orElseThrow();
        
        // then
        assertAll(
                () -> assertThat(interim.getApproval()).isEqualTo(APPROVAL),
                () -> assertThat(graduation.getStep()).isEqualTo(FINAL_REPORT)
        );
    }
    
    @Test
    @DisplayName("최종 보고서 폼을 반려한다.")
    void rejectInterim() {
        // given
        InterimSaveRequest interimSaveRequest = createInterimSaveRequest();
        Long interimId = interimService.saveInterim(interimSaveRequest.toServiceDto(), student.getId())
                .getId();
        String expectedRejectReason = "expectedRejectReason";
        
        // when
        interimService.rejectInterim(apply.getId(), expectedRejectReason);
        Interim interim = interimRepository.findById(interimId)
                .orElseThrow();
        
        // then
        assertAll(
                () -> assertThat(interim.getApproval()).isEqualTo(REJECT),
                () -> assertThat(graduation.getStep()).isEqualTo(INTERIM_REPORT),
                () -> assertThat(interim.getRejectReason()).isEqualTo(expectedRejectReason)
        );
    }
    
    private InterimSaveRequest createInterimSaveRequest() {
        return new InterimSaveRequest(
                "title",
                "division",
                "text",
                "plan"
        );
    }
}