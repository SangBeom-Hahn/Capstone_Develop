package com.kyonggi.Capstone_Develop.service;

import com.kyonggi.Capstone_Develop.domain.graduation.Apply;
import com.kyonggi.Capstone_Develop.domain.graduation.Graduation;
import com.kyonggi.Capstone_Develop.domain.student.Student;
import com.kyonggi.Capstone_Develop.exception.DuplicateApplyException;
import com.kyonggi.Capstone_Develop.exception.NoSuchMemberException;
import com.kyonggi.Capstone_Develop.repository.ApplyRepository;
import com.kyonggi.Capstone_Develop.repository.GraduationRepository;
import com.kyonggi.Capstone_Develop.repository.StudentRepository;
import com.kyonggi.Capstone_Develop.service.dto.graduation.GraduationSaveRequestDto;
import com.kyonggi.Capstone_Develop.service.dto.graduation.GraduationSaveResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GraduationService {
    private final GraduationRepository graduationRepository;
    private final StudentRepository studentRepository;

    private final ApplyRepository applyRepository;

    public GraduationSaveResponseDto save(
            GraduationSaveRequestDto graduationSaveRequestDto, Long studentId
    ) {
        List<Apply> applies = applyRepository.findAllByStudentId(studentId);
        validateDuplicateApply(applies, studentId);

        Graduation graduation = new Graduation(
                graduationSaveRequestDto.getMethod(),
                graduationSaveRequestDto.getStatus(),
                graduationSaveRequestDto.getStep(),
                graduationSaveRequestDto.getCapstoneCompletion(),
                graduationSaveRequestDto.getGraduationDate(),
                graduationSaveRequestDto.getProfessorName()
        );

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchMemberException(studentId));
        
        Graduation saveGraduation = graduationRepository.save(graduation);
        saveApply(student, graduation);
        return GraduationSaveResponseDto.from(saveGraduation);
    }

    private void validateDuplicateApply(List<Apply> applies, Long studentId) {
        if (!applies.isEmpty()) {
            throw new DuplicateApplyException(getStudentLoginId(studentId));
        }
    }

    private String getStudentLoginId(Long studentId) {
        return studentRepository.findById(studentId).get().getLoginId();
    }

    private void saveApply(Student student, Graduation graduation) {
        applyRepository.save(new Apply(student, graduation));
    }
}
