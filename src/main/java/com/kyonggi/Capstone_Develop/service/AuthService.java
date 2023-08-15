package com.kyonggi.Capstone_Develop.service;

import com.kyonggi.Capstone_Develop.domain.student.Student;
import com.kyonggi.Capstone_Develop.exception.IdPasswordMismatchException;
import com.kyonggi.Capstone_Develop.exception.NoSuchMemberException;
import com.kyonggi.Capstone_Develop.repository.StudentRepository;
import com.kyonggi.Capstone_Develop.service.dto.auth.TokenResponseDto;
import com.kyonggi.Capstone_Develop.support.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
    private final StudentRepository studentRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    
    public TokenResponseDto login(String loginId, String password) {
        Student findStudent = studentRepository.findByLoginId(loginId)
                .orElseThrow(() -> new NoSuchMemberException(loginId));
    
        validatePassword(findStudent, password);
        return issueTokenDto(findStudent.getId());
    }
    
    private void validatePassword(final Student findStudent, final String password) {
        if (!passwordEncoder.matches(password, findStudent.getPassword())) {
            throw new IdPasswordMismatchException();
        }
    }
    
    private TokenResponseDto issueTokenDto(Long studentId) {
        String accessToken = jwtTokenProvider.createToken(String.valueOf(studentId));
        return TokenResponseDto.of(accessToken, studentId);
    }
}
