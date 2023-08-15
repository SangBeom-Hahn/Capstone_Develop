package com.kyonggi.Capstone_Develop.service;

import com.kyonggi.Capstone_Develop.domain.student.Student;
import com.kyonggi.Capstone_Develop.exception.DuplicateLoginIdException;
import com.kyonggi.Capstone_Develop.exception.DuplicateStudentNumberException;
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
        validateStudentNumberHasDuplicate(studentRequestDto.getStudentNumber());
        String password = passwordEncoder.encode(studentRequestDto.getPassword());
    
        Student student = new Student(
                studentRequestDto.getLoginId(),
                password,
                studentRequestDto.getBirth(),
                studentRequestDto.getDepartment(),
                studentRequestDto.getGrade(),
                studentRequestDto.getPhoneNumber(),
                studentRequestDto.getSex(),
                studentRequestDto.getName(),
                studentRequestDto.getEmail(),
                studentRequestDto.getStudentNumber()
        );
        Student saveStudent = studentRepository.save(student);
        return StudentSignUpResponseDto.from(saveStudent);
    }

    private void validateLoginIdHasDuplicate(String loginId) {
        if (studentRepository.existsByLoginId(loginId)) {
            throw new DuplicateLoginIdException(loginId);
        }
    }

    private void validateStudentNumberHasDuplicate(String studentNumber) {
        if (studentRepository.existsByStudentNumber(studentNumber)) {
            throw new DuplicateStudentNumberException(studentNumber);
        }
    }
}
