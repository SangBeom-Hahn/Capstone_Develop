package com.kyonggi.Capstone_Develop.service.situation;

import com.kyonggi.Capstone_Develop.domain.graduation.Apply;
import com.kyonggi.Capstone_Develop.domain.graduation.Graduation;
import com.kyonggi.Capstone_Develop.domain.graduation.Status;
import com.kyonggi.Capstone_Develop.domain.graduation.Step;
import com.kyonggi.Capstone_Develop.domain.situation.Approval;
import com.kyonggi.Capstone_Develop.domain.situation.Proposal;
import com.kyonggi.Capstone_Develop.domain.student.Student;
import com.kyonggi.Capstone_Develop.exception.DuplicateProposalException;
import com.kyonggi.Capstone_Develop.exception.InvalidStepException;
import com.kyonggi.Capstone_Develop.exception.NoSuchApplyException;
import com.kyonggi.Capstone_Develop.exception.NoSuchMemberException;
import com.kyonggi.Capstone_Develop.repository.ApplyRepository;
import com.kyonggi.Capstone_Develop.repository.ProposalRepository;
import com.kyonggi.Capstone_Develop.repository.StudentRepository;
import com.kyonggi.Capstone_Develop.service.dto.situation.form.proposal.ProposalResponseDto;
import com.kyonggi.Capstone_Develop.service.dto.situation.form.proposal.ProposalSaveRequestDto;
import com.kyonggi.Capstone_Develop.service.dto.situation.form.proposal.ProposalSaveResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kyonggi.Capstone_Develop.domain.graduation.Status.REJECT;
import static com.kyonggi.Capstone_Develop.domain.graduation.Step.INTERIM_REPORT;
import static com.kyonggi.Capstone_Develop.domain.graduation.Step.PROPOSAL;
import static com.kyonggi.Capstone_Develop.domain.situation.Approval.APPROVAL;

@Service
@Transactional
@RequiredArgsConstructor
public class ProposalService {
    private final StudentRepository studentRepository;
    
    private final ApplyRepository applyRepository;
    
    private final ProposalRepository proposalRepository;
    
    public ProposalSaveResponseDto saveProposal(final ProposalSaveRequestDto proposalSaveRequestDto, final Long studentId) {
        Apply apply = applyRepository.findAllByStudentId(studentId)
                .stream()
                .findFirst()
                .orElseThrow(NoSuchApplyException::new);
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchMemberException(studentId));
        validateDuplicateProposal(student.getLoginId(), apply);
        
        Graduation graduation = apply.getGraduation();
        validateStep(graduation.getStep(), student.getLoginId());
        
        Proposal proposal = new Proposal(
                apply,
                proposalSaveRequestDto.getTitle(),
                proposalSaveRequestDto.getDivision(),
                graduation.getMethod().name(),
                proposalSaveRequestDto.getKeyword(),
                proposalSaveRequestDto.getContent(),
                Approval.UNAPPROVAL,
                null
        );
    
        Proposal saveProposal = proposalRepository.save(proposal);
        return ProposalSaveResponseDto.from(saveProposal);
    }
    
    private void validateDuplicateProposal(final String studentId, final Apply apply) {
        if (proposalRepository.existsByApply(apply)) {
            throw new DuplicateProposalException(studentId);
        }
    }
    
    private void validateStep(final Step step, final String loginId) {
        if (!step.equals(PROPOSAL)) {
            throw new InvalidStepException(loginId);
        }
    }
    
    public ProposalResponseDto findProposal(final Long applyId) {
        Apply apply = applyRepository.findById(applyId)
                .orElseThrow(NoSuchApplyException::new);
        Proposal proposal = proposalRepository.findByApply(apply)
                .orElseThrow(NoSuchApplyException::new);
        return ProposalResponseDto.of(proposal, apply);
    }
    
    public void approveProposal(final Long applyId) {
        Apply apply = applyRepository.findById(applyId)
                .orElseThrow(NoSuchApplyException::new);
        Proposal proposal = proposalRepository.findByApply(apply)
                .orElseThrow(NoSuchApplyException::new);
    
        updateProposal(apply, proposal, APPROVAL, INTERIM_REPORT);
    }
    
    public void rejectProposal(final Long applyId, final String rejectReason) {
        Apply apply = applyRepository.findById(applyId)
                .orElseThrow(NoSuchApplyException::new);
        Proposal proposal = proposalRepository.findByApply(apply)
                .orElseThrow(NoSuchApplyException::new);
    
        updateProposal(apply, proposal, Approval.REJECT, REJECT, rejectReason);
    }
    
    private void updateProposal(
            final Apply apply,
            final Proposal proposal,
            final Approval approval,
            final Step step
    ) {
        proposal.changeApproval(approval);
        changeGraduateStep(apply, step);
    }
    
    private void updateProposal(
            final Apply apply,
            final Proposal proposal,
            final Approval approval,
            final Status status,
            final String rejectReason
    ) {
        proposal.changeApproval(approval);
        proposal.changeRejectReason(rejectReason);
        changeGraduateStatus(apply, status);
    }
    
    private void changeGraduateStep(final Apply apply, final Step step) {
        apply.getGraduation()
                .changeStep(step);
    }
    
    private void changeGraduateStatus(final Apply apply, final Status status) {
        apply.getGraduation()
                .changeStatus(status);
    }
}
