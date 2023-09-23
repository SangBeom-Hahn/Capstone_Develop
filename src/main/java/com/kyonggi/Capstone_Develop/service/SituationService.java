package com.kyonggi.Capstone_Develop.service;

import com.kyonggi.Capstone_Develop.domain.graduation.Apply;
import com.kyonggi.Capstone_Develop.domain.graduation.Graduation;
import com.kyonggi.Capstone_Develop.domain.student.Student;
import com.kyonggi.Capstone_Develop.exception.NoSuchMemberException;
import com.kyonggi.Capstone_Develop.repository.ApplyRepository;
import com.kyonggi.Capstone_Develop.repository.StudentRepository;
import com.kyonggi.Capstone_Develop.service.dto.situation.SituationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SituationService {
    private final StudentRepository studentRepository;
    
    private final ApplyRepository applyRepository;
    
    public SituationResponseDto findSituation(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchMemberException(studentId));
        List<Apply> applies = applyRepository.findAllByStudentId(studentId);
        Graduation graduation = applies.get(0).getGraduation();
        List<Long> applyIds = applies.stream()
                .map(Apply::getId)
                .collect(Collectors.toList());
    
        return SituationResponseDto.of(student, graduation, applyIds);
    }
}
