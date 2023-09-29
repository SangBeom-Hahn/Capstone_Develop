package com.kyonggi.Capstone_Develop.service.situation;

import com.kyonggi.Capstone_Develop.domain.graduation.Apply;
import com.kyonggi.Capstone_Develop.domain.graduation.Graduation;
import com.kyonggi.Capstone_Develop.domain.graduation.Status;
import com.kyonggi.Capstone_Develop.domain.graduation.Step;
import com.kyonggi.Capstone_Develop.domain.situation.Approval;
import com.kyonggi.Capstone_Develop.domain.situation.Final;
import com.kyonggi.Capstone_Develop.domain.student.Student;
import com.kyonggi.Capstone_Develop.exception.DuplicateFinalException;
import com.kyonggi.Capstone_Develop.exception.InvalidStepException;
import com.kyonggi.Capstone_Develop.exception.NoSuchApplyException;
import com.kyonggi.Capstone_Develop.exception.NoSuchMemberException;
import com.kyonggi.Capstone_Develop.repository.ApplyRepository;
import com.kyonggi.Capstone_Develop.repository.FinalRepository;
import com.kyonggi.Capstone_Develop.repository.StudentRepository;
import com.kyonggi.Capstone_Develop.service.dto.situation.form.finalreport.FinalResponseDto;
import com.kyonggi.Capstone_Develop.service.dto.situation.form.finalreport.FinalSaveRequestDto;
import com.kyonggi.Capstone_Develop.service.dto.situation.form.finalreport.FinalSaveResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kyonggi.Capstone_Develop.domain.graduation.Step.FINAL_PASS;
import static com.kyonggi.Capstone_Develop.domain.graduation.Step.FINAL_REPORT;
import static com.kyonggi.Capstone_Develop.domain.situation.Approval.*;

@Service
@Transactional
@RequiredArgsConstructor
public class FinalService {
    private final StudentRepository studentRepository;
    
    private final ApplyRepository applyRepository;
    
    private final FinalRepository finalRepository;
    
    public FinalSaveResponseDto saveFinal(FinalSaveRequestDto proposalSaveRequestDto, Long studentId) {
        Apply apply = applyRepository.findAllByStudentId(studentId)
                .stream()
                .findFirst()
                .orElseThrow(NoSuchApplyException::new);
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchMemberException(studentId));
        validateDuplicateFinal(student.getLoginId(), apply);
        
        Graduation graduation = apply.getGraduation();
        validateStep(graduation.getStep(), student.getLoginId());
    
        Final finalReport = new Final(
                apply,
                "title",
                "division",
                "qualification",
                20,
                UNAPPROVAL,
                null
        );
    
        Final saveFinal = finalRepository.save(finalReport);
        return FinalSaveResponseDto.from(saveFinal);
    }
    
    private void validateDuplicateFinal(String studentId, Apply apply) {
        if (finalRepository.existsByApply(apply)) {
            throw new DuplicateFinalException(studentId);
        }
    }
    
    private void validateStep(Step step, String loginId) {
        if (!step.equals(FINAL_REPORT)) {
            throw new InvalidStepException(loginId);
        }
    }
    
    public FinalResponseDto findFinal(Long applyId) {
        Apply apply = applyRepository.findById(applyId)
                .orElseThrow(NoSuchApplyException::new);
        Final finalReport = finalRepository.findByApply(apply)
                .orElseThrow(NoSuchApplyException::new);
        return FinalResponseDto.of(finalReport, apply);
    }
    
    public void approveFinal(Long applyId) {
        Apply apply = applyRepository.findById(applyId)
                .orElseThrow(NoSuchApplyException::new);
        Final finalReport = finalRepository.findByApply(apply)
                .orElseThrow(NoSuchApplyException::new);
    
        updateFinal(apply, finalReport, APPROVAL, Status.APPROVAL, FINAL_PASS);
    }
    
    public void rejectFinal(Long applyId, String rejectReason) {
        Apply apply = applyRepository.findById(applyId)
                .orElseThrow(NoSuchApplyException::new);
        Final finalReport = finalRepository.findByApply(apply)
                .orElseThrow(NoSuchApplyException::new);
    
        updateFinal(apply, finalReport, REJECT, Status.REJECT, rejectReason);
    }
    
    private void updateFinal(
            Apply apply,
            Final finalReport,
            Approval approval,
            Status status,
            Step step
    ) {
        finalReport.changeApproval(approval);
        changeGraduateStep(apply, step);
        changeGraduateStatus(apply, status);
    }
    
    private void updateFinal(
            Apply apply,
            Final finalReport,
            Approval approval,
            Status status,
            String rejectReason
    ) {
        finalReport.changeApproval(approval);
        finalReport.changeRejectReason(rejectReason);
        changeGraduateStatus(apply, status);
    }
    
    private void changeGraduateStep(Apply apply, Step step) {
        apply.getGraduation()
                .changeStep(step);
    }
    
    private void changeGraduateStatus(Apply apply, Status status) {
        apply.getGraduation()
                .changeStatus(status);
    }
}
