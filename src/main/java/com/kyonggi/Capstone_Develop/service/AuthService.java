package com.kyonggi.Capstone_Develop.service;

import com.kyonggi.Capstone_Develop.domain.refreshtoken.RefreshToken;
import com.kyonggi.Capstone_Develop.domain.student.Student;
import com.kyonggi.Capstone_Develop.exception.IdPasswordMismatchException;
import com.kyonggi.Capstone_Develop.exception.NoSuchMemberException;
import com.kyonggi.Capstone_Develop.exception.NoSuchMemberIdException;
import com.kyonggi.Capstone_Develop.repository.RefreshTokenRepository;
import com.kyonggi.Capstone_Develop.repository.StudentRepository;
import com.kyonggi.Capstone_Develop.service.dto.auth.TokenResponseDto;
import com.kyonggi.Capstone_Develop.support.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
    private final StudentRepository studentRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;
    
    public TokenResponseDto login(String loginId, String password) {
        Student findStudent = studentRepository.findByLoginId(loginId)
                .orElseThrow(() -> new NoSuchMemberIdException(loginId));
    
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
        RefreshToken refreshToken = createRefreshToken(studentId);
        return TokenResponseDto.of(accessToken, refreshToken.getTokenValue(), studentId);
    }
    
    private RefreshToken createRefreshToken(final Long studentId) {
        final Student findStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchMemberException(studentId));
        final RefreshToken refreshToken = RefreshToken.createBy(findStudent.getId(), () -> UUID.randomUUID().toString());
        return refreshTokenRepository.save(refreshToken);
    }
    
    public void logout(String refreshToken) {
        refreshTokenRepository.deleteByTokenValue(refreshToken);
    }
}
