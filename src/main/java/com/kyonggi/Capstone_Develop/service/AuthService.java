package com.kyonggi.Capstone_Develop.service;

import com.kyonggi.Capstone_Develop.domain.refreshtoken.RefreshToken;
import com.kyonggi.Capstone_Develop.domain.student.RoleType;
import com.kyonggi.Capstone_Develop.domain.student.Student;
import com.kyonggi.Capstone_Develop.exception.IdPasswordMismatchException;
import com.kyonggi.Capstone_Develop.exception.NoSuchMemberException;
import com.kyonggi.Capstone_Develop.exception.NoSuchMemberIdException;
import com.kyonggi.Capstone_Develop.repository.RefreshTokenRepository;
import com.kyonggi.Capstone_Develop.repository.StudentRepository;
import com.kyonggi.Capstone_Develop.service.dto.auth.RefreshTokenSaveResponseDto;
import com.kyonggi.Capstone_Develop.service.dto.auth.TokenResponseDto;
import com.kyonggi.Capstone_Develop.support.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
    private final StudentRepository studentRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final RedisTemplate<String, RefreshTokenSaveResponseDto> redisTemplate;
    
    public TokenResponseDto login(String loginId, String password) {
        Student findStudent = studentRepository.findByLoginId(loginId)
                .orElseThrow(() -> new NoSuchMemberIdException(loginId));
    
        validatePassword(findStudent, password);
        return issueTokenDto(findStudent.getId(), findStudent.getRoleType());
    }
    
    private void validatePassword(final Student findStudent, final String password) {
        if (!passwordEncoder.matches(password, findStudent.getPassword())) {
            throw new IdPasswordMismatchException();
        }
    }
    
    private TokenResponseDto issueTokenDto(Long studentId, RoleType roleType) {
        Map<String, Object> payload = createPayloadMap(studentId, roleType);
    
        String accessToken = jwtTokenProvider.createToken(payload);

        RefreshToken refreshToken = createRefreshToken(studentId);
        return TokenResponseDto.of(accessToken, refreshToken.getTokenValue(), studentId);
    }
    
    private Map<String, Object> createPayloadMap(Long studentId, RoleType roleType) {
        return JwtTokenProvider.payloadBuilder()
                .setSubject(String.valueOf(studentId))
                .put(roleType.name())
                .build();
    }
    
    private RefreshToken createRefreshToken(final Long studentId) {
        final Student findStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchMemberException(studentId));

        // 여기서 레디스에 저장
        final RefreshToken refreshToken = RefreshToken.createBy(findStudent.getId(), () -> UUID.randomUUID().toString());
        saveRefreshToken(studentId, refreshToken);

        return refreshToken;
    }

    private void saveRefreshToken(Long studentId, RefreshToken refreshToken) {
        redisTemplate.opsForValue()
                .set(
                        String.valueOf(studentId),
                        RefreshTokenSaveResponseDto.from(refreshToken)
                );
    }

    public void logout(Long id) {
        redisTemplate.delete(String.valueOf(id));
    }
}
