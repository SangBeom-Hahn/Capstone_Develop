package com.kyonggi.Capstone_Develop.support;

import com.kyonggi.Capstone_Develop.domain.student.RoleType;
import com.kyonggi.Capstone_Develop.exception.TokenInvalidExpiredException;
import com.kyonggi.Capstone_Develop.exception.TokenInvalidFormException;
import com.kyonggi.Capstone_Develop.exception.TokenInvalidSecretKeyException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

import static com.kyonggi.Capstone_Develop.domain.student.RoleType.STUDENT;
import static com.kyonggi.Capstone_Develop.utils.ConstantMessage.TOKEN_NUMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class JwtTokenProviderTest {
    
    @Autowired
    protected JwtTokenProvider jwtTokenProvider;
    
    @Value("${jwt.token.secret-key}")
    private String secretKey;
    
    private final JwtTokenProvider invalidSecretKeyJwtTokenProvider
            = new JwtTokenProvider(
            "invalidSecretKeyInvalidSecretKeyInvalidSecretKeyInvalidSecretKey",
            8640000L
    );
    
    private Map<String, Object> createPayloadMap(Long studentId, RoleType roleType) {
        Map<String, Object> payload = JwtTokenProvider.payloadBuilder()
                .setSubject(String.valueOf(studentId))
                .put(roleType.name())
                .build();
        
        return payload;
    }
    
    @Test
    @DisplayName("유효하지 않은 토큰 형식의 토큰으로 payload를 조회할 경우 예외가 발생한다.")
    void getPayloadByInvalidToken() {
        Assertions.assertThatThrownBy(() -> jwtTokenProvider.getPayload(null))
                .isInstanceOf(TokenInvalidFormException.class)
                .hasMessage("올바르지 않은 토큰입니다.");
    }
    
    @Test
    @DisplayName("만료된 토큰으로 payload를 조회할 경우 예외가 발생한다.")
    void getPayloadByExpiredToken() {
        // given
        final String expiredToken = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .setSubject(String.valueOf(TOKEN_NUMBER))
                .setExpiration(new Date((new Date()).getTime() - 1))
                .compact();
        
        // then
        assertThatThrownBy(() -> jwtTokenProvider.getPayload(expiredToken))
                .isInstanceOf(TokenInvalidExpiredException.class)
                .hasMessage("토큰의 유효기간이 만료됐습니다.");
    }
    
    @Test
    @DisplayName("시크릿 키가 틀린 토큰 정보로 payload를 조회할 경우 예외를 발생시킨다.")
    void getPayloadByWrongSecretKeyToken() {
        // given
        Map<String, Object> payloadMap = createPayloadMap(TOKEN_NUMBER, STUDENT);
        final String invalidSecretToken = invalidSecretKeyJwtTokenProvider.createToken(payloadMap);
        
        // then
        assertThatThrownBy(() -> jwtTokenProvider.getPayload(invalidSecretToken))
                .isInstanceOf(TokenInvalidSecretKeyException.class)
                .hasMessage(String.format("토큰의 secret key가 변조됐습니다. 해킹의 우려가 존재합니다. token={%s}", invalidSecretToken));
    }
    
    @Test
    @DisplayName("토큰이 올바르게 생성된다.")
    void createToken() {
        Map<String, Object> payloadMap = createPayloadMap(TOKEN_NUMBER, STUDENT);
        
        final String token = jwtTokenProvider.createToken(payloadMap);
        assertThat(token).isNotNull();
    }
    
    @Test
    @DisplayName("올바른 토큰 정보로 payload를 조회한다.")
    void getPayloadByValidToken() {
        Map<String, Object> payloadMap = createPayloadMap(TOKEN_NUMBER, STUDENT);
        final String token = jwtTokenProvider.createToken(payloadMap);
        
        assertThat(jwtTokenProvider.getPayload(token))
                .isEqualTo(String.valueOf(TOKEN_NUMBER));
    }
    
    @Test
    @DisplayName("올바른 토큰 정보로 role을 조회한다.")
    void getRolePayloadValidToken() {
        Map<String, Object> payloadMap = createPayloadMap(TOKEN_NUMBER, STUDENT);
        final String token = jwtTokenProvider.createToken(payloadMap);
    
        assertThat(jwtTokenProvider.getRolePayload(token))
                .isEqualTo(STUDENT.name());
    }
}