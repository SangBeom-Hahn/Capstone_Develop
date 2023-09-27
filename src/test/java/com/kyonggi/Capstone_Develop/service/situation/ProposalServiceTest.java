package com.kyonggi.Capstone_Develop.service.situation;

import com.kyonggi.Capstone_Develop.controller.dto.situation.proposal.ProposalSaveRequest;
import com.kyonggi.Capstone_Develop.domain.graduation.*;
import com.kyonggi.Capstone_Develop.domain.situation.Proposal;
import com.kyonggi.Capstone_Develop.domain.student.*;
import com.kyonggi.Capstone_Develop.exception.DuplicateProposalException;
import com.kyonggi.Capstone_Develop.exception.InvalidStepException;
import com.kyonggi.Capstone_Develop.service.ServiceTest;
import com.kyonggi.Capstone_Develop.service.dto.situation.form.proposal.ProposalResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.kyonggi.Capstone_Develop.domain.graduation.Step.INTERIM_REPORT;
import static com.kyonggi.Capstone_Develop.domain.graduation.Step.PROPOSAL;
import static com.kyonggi.Capstone_Develop.domain.situation.Approval.APPROVAL;
import static com.kyonggi.Capstone_Develop.domain.situation.Approval.REJECT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class ProposalServiceTest extends ServiceTest {
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
                PROPOSAL,
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
    void throwException_DuplicateProposal() {
        // given
        ProposalSaveRequest proposalSaveRequest1 = createProposalSaveRequest();
        ProposalSaveRequest proposalSaveRequest2 = createProposalSaveRequest();
    
        // when
        proposalService.saveProposal(proposalSaveRequest1.toServiceDto(), student.getId());
        
        // then
        assertThatThrownBy(() -> proposalService.saveProposal(proposalSaveRequest2.toServiceDto(), student.getId()))
                .isInstanceOf(DuplicateProposalException.class)
                .hasMessage("이미 제안서 제출 되었습니다. loginId={%s}", student.getLoginId());
    }
    
    @Test
    @DisplayName("제안서 단계가 아닌 학생은 예외가 발생한다.")
    void throwException_invalidStep() {
        // given
        ProposalSaveRequest proposalSaveRequest = createProposalSaveRequest();
        
        // then
        assertThatThrownBy(() -> proposalService.saveProposal(proposalSaveRequest.toServiceDto(), student2.getId()))
                .isInstanceOf(InvalidStepException.class)
                .hasMessage("올바르지 않은 단계의 신청입니다. loginId={%s}", student2.getLoginId());
    }
    
    @Test
    @DisplayName("제안서 폼을 저장하고 id로 조회한다.")
    void saveProposalAndFind() {
        // given
        ProposalSaveRequest proposalSaveRequest = createProposalSaveRequest();
        Long proposalId = proposalService.saveProposal(proposalSaveRequest.toServiceDto(), student.getId())
                .getId();
    
        // when
        ProposalResponseDto proposalResponseDto = proposalService.findProposal(apply.getId());
    
        // then
        assertThat(proposalResponseDto).extracting("proposalId", "applyId", "title", "division", "keyword",
                "content", "rejectReason")
                .containsExactly(
                        proposalId,
                        apply.getId(),
                        proposalSaveRequest.getTitle(),
                        proposalSaveRequest.getDivision(),
                        proposalSaveRequest.getKeyword(),
                        proposalSaveRequest.getContent(),
                        null
                );
    }

    @Test
    @DisplayName("제안서 폼을 승인한다.")
    void approveProposal() {
        // given
        ProposalSaveRequest proposalSaveRequest = createProposalSaveRequest();
        Long proposalId = proposalService.saveProposal(proposalSaveRequest.toServiceDto(), student.getId())
                .getId();

        // when
        proposalService.approveProposal(apply.getId());
        Proposal proposal = proposalRepository.findById(proposalId)
                .orElseThrow();
    
        // then
        assertAll(
                () -> assertThat(proposal.getApproval()).isEqualTo(APPROVAL),
                () -> assertThat(graduation.getStep()).isEqualTo(INTERIM_REPORT)
        );
    }

    @Test
    @DisplayName("제안서 폼을 반려한다.")
    void rejectProposal() {
        ProposalSaveRequest proposalSaveRequest = createProposalSaveRequest();
        Long proposalId = proposalService.saveProposal(proposalSaveRequest.toServiceDto(), student.getId())
                .getId();
        String expectedRejectReason = "expectedRejectReason";
    
        // when
        proposalService.rejectProposal(apply.getId(), expectedRejectReason);
        Proposal proposal = proposalRepository.findById(proposalId)
                .orElseThrow();
    
        // then
        assertAll(
                () -> assertThat(proposal.getApproval()).isEqualTo(REJECT),
                () -> assertThat(graduation.getStep()).isEqualTo(PROPOSAL),
                () -> assertThat(graduation.getStatus()).isEqualTo(Status.REJECT),
                () -> assertThat(proposal.getRejectReason()).isEqualTo(expectedRejectReason)
                
        );
    }
    
    private ProposalSaveRequest createProposalSaveRequest() {
        return new ProposalSaveRequest(
                "title",
                "division",
                "keyword",
                "content"
        );
    }
}
