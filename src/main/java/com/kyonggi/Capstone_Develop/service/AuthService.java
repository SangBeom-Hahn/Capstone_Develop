package com.kyonggi.Capstone_Develop.service;

import com.kyonggi.Capstone_Develop.domain.refreshtoken.RefreshToken;
import com.kyonggi.Capstone_Develop.domain.student.RoleType;
import com.kyonggi.Capstone_Develop.domain.student.Student;
import com.kyonggi.Capstone_Develop.exception.IdPasswordMismatchException;
import com.kyonggi.Capstone_Develop.exception.NoSuchMemberException;
import com.kyonggi.Capstone_Develop.exception.NoSuchMemberIdException;
import com.kyonggi.Capstone_Develop.exception.RefreshTokenInvalidException;
import com.kyonggi.Capstone_Develop.repository.RefreshTokenRepository;
import com.kyonggi.Capstone_Develop.repository.StudentRepository;
import com.kyonggi.Capstone_Develop.service.dto.auth.AccessTokenResponseDto;
import com.kyonggi.Capstone_Develop.service.dto.auth.AccessTokenSaveResponseDto;
import com.kyonggi.Capstone_Develop.service.dto.auth.TokenResponseDto;
import com.kyonggi.Capstone_Develop.support.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
    private final StudentRepository studentRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RedisTemplate<String, AccessTokenSaveResponseDto> redisTemplate;
    
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
        saveRefreshToken(accessToken, studentId);

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

        final RefreshToken refreshToken =
                RefreshToken.createBy(findStudent.getId(), () -> UUID.randomUUID().toString());
        refreshTokenRepository.save(refreshToken);

        return refreshToken;
    }

    private void saveRefreshToken(String accessToken, Long studentId) {
        redisTemplate.opsForValue()
                .set(
                        accessToken,
                        AccessTokenSaveResponseDto.of(accessToken, studentId)
                );
    }

    @Transactional(noRollbackFor = RefreshTokenInvalidException.class)
    public AccessTokenResponseDto renewalToken(final String refreshToken) {
        RefreshToken findRefreshToken = findValidRefreshToken(refreshToken);
        findRefreshToken.extendsExpired();

        final Map<String, Object> payload = createPayloadMap(findRefreshToken.getMemberId());
        final String accessToken = jwtTokenProvider.createToken(payload);

        return new AccessTokenResponseDto(accessToken);
    }

    private RefreshToken findValidRefreshToken(String refreshToken) {
        RefreshToken findRefreshToken = refreshTokenRepository.findByTokenValue(refreshToken)
                .orElseThrow(RefreshTokenInvalidException::new);

        if (findRefreshToken.isExpired()) {
            refreshTokenRepository.deleteByExpiredTimeBefore(LocalDateTime.now());
            throw new RefreshTokenInvalidException();
        }

        return findRefreshToken;
    }

    private Map<String, Object> createPayloadMap(Long studentId) {
        return JwtTokenProvider.payloadBuilder()
                .setSubject(String.valueOf(studentId))
                .build();
    }

    public void logout(String accessToken, String refreshToken) {
        redisTemplate.delete(accessToken);
        refreshTokenRepository.deleteByTokenValue(refreshToken);
    }
}
