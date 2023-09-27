package com.kyonggi.Capstone_Develop.service.situation;

import com.kyonggi.Capstone_Develop.domain.graduation.Apply;
import com.kyonggi.Capstone_Develop.domain.graduation.Graduation;
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

import static com.kyonggi.Capstone_Develop.domain.graduation.Step.INTERIM_REPORT;
import static com.kyonggi.Capstone_Develop.domain.graduation.Step.PROPOSAL;
import static com.kyonggi.Capstone_Develop.domain.situation.Approval.APPROVAL;
import static com.kyonggi.Capstone_Develop.domain.situation.Approval.REJECT;

@Service
@Transactional
@RequiredArgsConstructor
public class ProposalService {
    private final StudentRepository studentRepository;
    
    private final ApplyRepository applyRepository;
    
    private final ProposalRepository proposalRepository;
    
    public ProposalSaveResponseDto saveProposal(ProposalSaveRequestDto proposalSaveRequestDto, Long studentId) {
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
    
    private void validateDuplicateProposal(String studentId, Apply apply) {
        if (proposalRepository.existsByApply(apply)) {
            throw new DuplicateProposalException(studentId);
        }
    }
    
    private void validateStep(Step step, String loginId) {
        if (!step.equals(PROPOSAL)) {
            throw new InvalidStepException(loginId);
        }
    }
    
    public ProposalResponseDto findProposal(Long applyId) {
        Apply apply = applyRepository.findById(applyId)
                .orElseThrow(NoSuchApplyException::new);
        Proposal proposal = proposalRepository.findByApply(apply)
                .orElseThrow(NoSuchApplyException::new);
        return ProposalResponseDto.of(proposal, apply);
    }
    
    public void approveProposal(Long applyId) {
        Apply apply = applyRepository.findById(applyId)
                .orElseThrow(NoSuchApplyException::new);
        Proposal proposal = proposalRepository.findByApply(apply)
                .orElseThrow(NoSuchApplyException::new);
    
        updateProposal(apply, proposal, APPROVAL, INTERIM_REPORT);
    }
    
    public void rejectProposal(Long applyId, String rejectReason) {
        Apply apply = applyRepository.findById(applyId)
                .orElseThrow(NoSuchApplyException::new);
        Proposal proposal = proposalRepository.findByApply(apply)
                .orElseThrow(NoSuchApplyException::new);
    
        updateProposal(proposal, REJECT, rejectReason);
    }
    
    private static void updateProposal(
            Apply apply,
            Proposal proposal,
            Approval approval,
            Step step
    ) {
        proposal.changeApproval(approval);
        changeGraduate(apply, step);
    }
    
    private static void updateProposal(
            Proposal proposal,
            Approval approval,
            String rejectReason
    ) {
        proposal.changeApproval(approval);
        proposal.changeRejectReason(rejectReason);
    }
    
    private static void changeGraduate(Apply apply, Step step) {
        apply.getGraduation()
                .changeStep(step);
    }
}
