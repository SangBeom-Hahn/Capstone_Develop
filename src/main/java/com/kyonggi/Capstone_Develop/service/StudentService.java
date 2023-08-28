package com.kyonggi.Capstone_Develop.service;

import com.kyonggi.Capstone_Develop.domain.student.RoleType;
import com.kyonggi.Capstone_Develop.domain.student.Student;
import com.kyonggi.Capstone_Develop.exception.DuplicateLoginIdException;
import com.kyonggi.Capstone_Develop.repository.StudentRepository;
import com.kyonggi.Capstone_Develop.service.dto.student.StudentRequestDto;
import com.kyonggi.Capstone_Develop.service.dto.student.StudentSignUpResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    public StudentSignUpResponseDto save(StudentRequestDto studentRequestDto) {
        validateLoginIdHasDuplicate(studentRequestDto.getLoginId());
        String password = passwordEncoder.encode(studentRequestDto.getPassword());
    
        Student student = new Student(
                studentRequestDto.getLoginId(),
                password,
                studentRequestDto.getBirth(),
                studentRequestDto.getDepartment(),
                studentRequestDto.getPhoneNumber(),
                studentRequestDto.getSex(),
                studentRequestDto.getName(),
                studentRequestDto.getEmail(),
                RoleType.STUDENT,
                studentRequestDto.getAnswerPw(),
                studentRequestDto.getClassification()
        );
        Student saveStudent = studentRepository.save(student);
        return StudentSignUpResponseDto.from(saveStudent);
    }

    public void validateLoginIdHasDuplicate(String loginId) {
        if (studentRepository.existsByLoginId(loginId)) {
            throw new DuplicateLoginIdException(loginId);
        }
    }
}
