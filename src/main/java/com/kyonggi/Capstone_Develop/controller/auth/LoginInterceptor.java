package com.kyonggi.Capstone_Develop.controller.auth;

import com.kyonggi.Capstone_Develop.exception.InvalidAuthMemberException;
import com.kyonggi.Capstone_Develop.service.dto.auth.RefreshTokenSaveResponseDto;
import com.kyonggi.Capstone_Develop.support.AuthorizationExtractor;
import com.kyonggi.Capstone_Develop.support.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {
    
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate<String, RefreshTokenSaveResponseDto> redisTemplate;
    
    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response,
                             final Object handler) {
        final String token = AuthorizationExtractor.extract(request);
        String key = jwtTokenProvider.getPayload(token);

        if (hasRefreshToken(key)) {
            jwtTokenProvider.validateAbleToken(token);
        } else {
            throw new InvalidAuthMemberException(key);
        }

        return true;
    }

    private boolean hasRefreshToken(String key) {
        return redisTemplate.hasKey(key) && getKey(key).equals(key);
    }

    public String getKey(String key) {
        return redisTemplate.opsForValue()
                .get(key)
                .getMemberId()
                .toString();
    }
}
