package com.kyonggi.Capstone_Develop.config;

import com.kyonggi.Capstone_Develop.controller.auth.AdminInterceptor;
import com.kyonggi.Capstone_Develop.controller.auth.AuthenticationPrincipalArgumentResolver;
import com.kyonggi.Capstone_Develop.controller.auth.LoginInterceptor;
import com.kyonggi.Capstone_Develop.service.dto.auth.RefreshTokenSaveResponseDto;
import com.kyonggi.Capstone_Develop.support.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class AuthenticationPrincipalConfig implements WebMvcConfigurer {
    
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate<String, RefreshTokenSaveResponseDto> redisTemplate;
    
    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor(jwtTokenProvider, redisTemplate))
                .order(1)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/login/auth")
                .excludePathPatterns("/api/logout")
                .excludePathPatterns("/api/user")
                .excludePathPatterns("/api/user/duplicate-check");
    
        registry.addInterceptor(new AdminInterceptor(jwtTokenProvider))
                .order(2)
                .addPathPatterns("/api/admins/**");
    }
    
    @Override
    public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new AuthenticationPrincipalArgumentResolver(jwtTokenProvider));
    }
}
