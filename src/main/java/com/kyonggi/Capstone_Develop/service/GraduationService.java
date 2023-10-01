package com.kyonggi.Capstone_Develop.service;

import com.kyonggi.Capstone_Develop.domain.graduation.Apply;
import com.kyonggi.Capstone_Develop.domain.graduation.Graduation;
import com.kyonggi.Capstone_Develop.domain.graduation.Status;
import com.kyonggi.Capstone_Develop.domain.graduation.Step;
import com.kyonggi.Capstone_Develop.domain.student.Student;
import com.kyonggi.Capstone_Develop.exception.DuplicateApplyException;
import com.kyonggi.Capstone_Develop.exception.NoSuchMemberException;
import com.kyonggi.Capstone_Develop.repository.ApplyRepository;
import com.kyonggi.Capstone_Develop.repository.GraduationRepository;
import com.kyonggi.Capstone_Develop.repository.StudentRepository;
import com.kyonggi.Capstone_Develop.service.dto.graduation.GraduationResponseDto;
import com.kyonggi.Capstone_Develop.service.dto.graduation.GraduationSaveRequestDto;
import com.kyonggi.Capstone_Develop.service.dto.graduation.GraduationSaveResponseDto;
import com.kyonggi.Capstone_Develop.service.dto.graduation.GraduationsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class GraduationService {
    private final GraduationRepository graduationRepository;
    
    private final StudentRepository studentRepository;

    private final ApplyRepository applyRepository;

    public GraduationSaveResponseDto save(
            final GraduationSaveRequestDto graduationSaveRequestDto, final Long studentId
    ) {
        List<Apply> applies = applyRepository.findAllByStudentId(studentId);
        validateDuplicateApply(applies, studentId);

        Graduation graduation = new Graduation(
                graduationSaveRequestDto.getMethod(),
                Status.UNAPPROVAL,
                Step.RECEIVED,
                null,
                null,
                null
        );

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchMemberException(studentId));
        
        Graduation saveGraduation = graduationRepository.save(graduation);
        saveApply(student, graduation);
        return GraduationSaveResponseDto.from(saveGraduation);
    }

    private void validateDuplicateApply(final List<Apply> applies, final Long studentId) {
        if (!applies.isEmpty()) {
            throw new DuplicateApplyException(getStudentLoginId(studentId));
        }
    }

    private String getStudentLoginId(final Long studentId) {
        return studentRepository.findById(studentId).get().getLoginId();
    }

    private void saveApply(final Student student, final Graduation graduation) {
        // cascade ALL
        new Apply(student, graduation);
    }
    
    public GraduationsResponseDto findAllGraduation() {
        List<Graduation> graduations = graduationRepository.findAll();
        List<GraduationResponseDto> graduationResponseDtos = graduations.stream()
                .map(graduation -> GraduationResponseDto.of(graduation, graduation.getApply()))
                .collect(Collectors.toList());
    
        return GraduationsResponseDto.from(graduationResponseDtos);
    }
}
