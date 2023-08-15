package com.kyonggi.Capstone_Develop.support;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class JwtTokenProviderTest {
    @Autowired
    protected JwtTokenProvider jwtTokenProvider;
    
    @Test
    @DisplayName("토큰이 올바르게 생성된다.")
    void createToken() {
        final String payload = String.valueOf(1L);
        
        final String token = jwtTokenProvider.createToken(payload);
        System.out.println(token);
        assertThat(token).isNotNull();
    }
    
    @DisplayName("올바른 토큰 정보로 payload를 조회한다.")
    @Test
    void getPayloadByValidToken() {
        final String payload = String.valueOf(1L);
        
        final String token = jwtTokenProvider.createToken(payload);
        
        assertThat(jwtTokenProvider.getPayload(token)).isEqualTo(payload);
    }
}