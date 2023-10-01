package com.kyonggi.Capstone_Develop.service.situation;

import com.kyonggi.Capstone_Develop.domain.graduation.Apply;
import com.kyonggi.Capstone_Develop.domain.graduation.Graduation;
import com.kyonggi.Capstone_Develop.domain.graduation.Status;
import com.kyonggi.Capstone_Develop.domain.graduation.Step;
import com.kyonggi.Capstone_Develop.domain.situation.Approval;
import com.kyonggi.Capstone_Develop.domain.situation.Interim;
import com.kyonggi.Capstone_Develop.domain.student.Student;
import com.kyonggi.Capstone_Develop.exception.DuplicateInterimException;
import com.kyonggi.Capstone_Develop.exception.InvalidStepException;
import com.kyonggi.Capstone_Develop.exception.NoSuchApplyException;
import com.kyonggi.Capstone_Develop.exception.NoSuchMemberException;
import com.kyonggi.Capstone_Develop.repository.ApplyRepository;
import com.kyonggi.Capstone_Develop.repository.InterimRepository;
import com.kyonggi.Capstone_Develop.repository.StudentRepository;
import com.kyonggi.Capstone_Develop.service.dto.situation.form.interim.InterimResponseDto;
import com.kyonggi.Capstone_Develop.service.dto.situation.form.interim.InterimSaveRequestDto;
import com.kyonggi.Capstone_Develop.service.dto.situation.form.interim.InterimSaveResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kyonggi.Capstone_Develop.domain.graduation.Step.INTERIM_REPORT;
import static com.kyonggi.Capstone_Develop.domain.situation.Approval.*;

@Service
@Transactional
@RequiredArgsConstructor
public class InterimService {
    private final StudentRepository studentRepository;
    
    private final ApplyRepository applyRepository;
    
    private final InterimRepository interimRepository;
    
    public InterimSaveResponseDto saveInterim(final InterimSaveRequestDto interimSaveRequestDto, final Long studentId) {
        Apply apply = applyRepository.findAllByStudentId(studentId)
                .stream()
                .findFirst()
                .orElseThrow(NoSuchApplyException::new);
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchMemberException(studentId));
        validateDuplicateInterim(student.getLoginId(), apply);
        
        Graduation graduation = apply.getGraduation();
        validateStep(graduation.getStep(), student.getLoginId());
    
        Interim interim = new Interim(
                apply,
                interimSaveRequestDto.getTitle(),
                interimSaveRequestDto.getDivision(),
                interimSaveRequestDto.getText(),
                interimSaveRequestDto.getPlan(),
                UNAPPROVAL,
                null
        );
    
        Interim saveInterim = interimRepository.save(interim);
        return InterimSaveResponseDto.from(saveInterim);
    }
    
    private void validateDuplicateInterim(final String studentId, final Apply apply) {
        if (interimRepository.existsByApply(apply)) {
            throw new DuplicateInterimException(studentId);
        }
    }
    
    private void validateStep(final Step step, final String loginId) {
        if (!step.equals(INTERIM_REPORT)) {
            throw new InvalidStepException(loginId);
        }
    }
    
    public InterimResponseDto findInterim(final Long applyId) {
        Apply apply = applyRepository.findById(applyId)
                .orElseThrow(NoSuchApplyException::new);
        Interim interim = interimRepository.findByApply(apply)
                .orElseThrow(NoSuchApplyException::new);
        return InterimResponseDto.of(interim, apply);
    }
    
    public void approveInterim(final Long applyId) {
        Apply apply = applyRepository.findById(applyId)
                .orElseThrow(NoSuchApplyException::new);
        Interim interim = interimRepository.findByApply(apply)
                .orElseThrow(NoSuchApplyException::new);
        
        updateInterim(apply, interim, APPROVAL, Step.FINAL_REPORT);
    }
    
    public void rejectInterim(final Long applyId, final String rejectReason) {
        Apply apply = applyRepository.findById(applyId)
                .orElseThrow(NoSuchApplyException::new);
        Interim interim = interimRepository.findByApply(apply)
                .orElseThrow(NoSuchApplyException::new);
        
        updateInterim(apply, interim, REJECT, Status.REJECT, rejectReason);
    }
    
    private void updateInterim(
            final Apply apply,
            final Interim interim,
            final Approval approval,
            final Step step
    ) {
        interim.changeApproval(approval);
        changeGraduateStep(apply, step);
    }
    
    private void updateInterim(
            final Apply apply,
            final Interim interim,
            final Approval approval,
            final Status status,
            final String rejectReason
    ) {
        interim.changeApproval(approval);
        interim.changeRejectReason(rejectReason);
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
