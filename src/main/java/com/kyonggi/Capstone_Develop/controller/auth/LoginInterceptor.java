package com.kyonggi.Capstone_Develop.controller.auth;

import com.kyonggi.Capstone_Develop.exception.InvalidAuthMemberException;
import com.kyonggi.Capstone_Develop.service.dto.auth.AccessTokenSaveResponseDto;
import com.kyonggi.Capstone_Develop.support.AuthorizationExtractor;
import com.kyonggi.Capstone_Develop.support.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {
    
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate<String, AccessTokenSaveResponseDto> redisTemplate;
    
    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response,
                             final Object handler) {
        final String token = AuthorizationExtractor.extract(request);
        String key = jwtTokenProvider.getPayload(token);

        if (hasRefreshToken(token)) {
            jwtTokenProvider.validateAbleToken(token);
        } else {
            throw new InvalidAuthMemberException(key);
        }

        return true;
    }

    private boolean hasRefreshToken(String token) {
        return redisTemplate.hasKey(token) && getValue(token) != null;
    }

    public AccessTokenSaveResponseDto getValue(String token) {
        return redisTemplate.opsForValue()
                .get(token);
    }
}
