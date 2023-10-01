package com.kyonggi.Capstone_Develop.service.situation;

import com.kyonggi.Capstone_Develop.domain.graduation.Apply;
import com.kyonggi.Capstone_Develop.domain.graduation.Graduation;
import com.kyonggi.Capstone_Develop.domain.situation.Approval;
import com.kyonggi.Capstone_Develop.domain.situation.Submit;
import com.kyonggi.Capstone_Develop.domain.student.Student;
import com.kyonggi.Capstone_Develop.exception.DuplicateSubmitException;
import com.kyonggi.Capstone_Develop.exception.NoSuchApplyException;
import com.kyonggi.Capstone_Develop.exception.NoSuchMemberException;
import com.kyonggi.Capstone_Develop.repository.ApplyRepository;
import com.kyonggi.Capstone_Develop.repository.StudentRepository;
import com.kyonggi.Capstone_Develop.repository.SubmitRepository;
import com.kyonggi.Capstone_Develop.service.dto.situation.form.submit.SubmitResponseDto;
import com.kyonggi.Capstone_Develop.service.dto.situation.form.submit.SubmitSaveRequestDto;
import com.kyonggi.Capstone_Develop.service.dto.situation.form.submit.SubmitSaveResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kyonggi.Capstone_Develop.domain.graduation.Step.PROPOSAL;

@Service
@Transactional
@RequiredArgsConstructor
public class SubmitService {
    private final StudentRepository studentRepository;
    
    private final ApplyRepository applyRepository;
    
    private final SubmitRepository submitRepository;
    
    public SubmitSaveResponseDto saveSubmit(final SubmitSaveRequestDto submitSaveRequestDto, final Long studentId) {
        Apply apply = applyRepository.findAllByStudentId(studentId)
                .stream()
                .findFirst()
                .orElseThrow(NoSuchApplyException::new);
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchMemberException(studentId));
        validateDuplicateSubmit(student.getLoginId(), apply);
    
        Submit submit = new Submit(
                apply,
                submitSaveRequestDto.getProfessorName(),
                submitSaveRequestDto.getGraduationDate(),
                submitSaveRequestDto.getCapstoneCompletion(),
                Approval.APPROVAL
        );
        
        Graduation graduation = apply.getGraduation();
        graduation.changeStep(PROPOSAL);
        
        Submit saveSubmit = submitRepository.save(submit);
        return SubmitSaveResponseDto.from(saveSubmit);
    }
    
    private void validateDuplicateSubmit(final String studentId, final Apply apply) {
        if (submitRepository.existsByApply(apply)) {
            throw new DuplicateSubmitException(studentId);
        }
    }
    
    public SubmitResponseDto findSubmit(final Long applyId) {
        Apply apply = applyRepository.findById(applyId)
                .orElseThrow(NoSuchApplyException::new);
        Submit submit = submitRepository.findByApply(apply)
                .orElseThrow(NoSuchApplyException::new);
        return SubmitResponseDto.from(submit);
    }
}
