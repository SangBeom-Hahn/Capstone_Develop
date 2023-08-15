package com.kyonggi.Capstone_Develop.config;

import com.kyonggi.Capstone_Develop.controller.auth.AuthenticationPrincipalArgumentResolver;
import com.kyonggi.Capstone_Develop.controller.auth.LoginInterceptor;
import com.kyonggi.Capstone_Develop.support.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class AuthenticationPrincipalConfig implements WebMvcConfigurer {
    
    private final JwtTokenProvider jwtTokenProvider;
    
    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor(jwtTokenProvider))
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/login/auth")
                .excludePathPatterns("/api/students");
    }
    
    @Override
    public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new AuthenticationPrincipalArgumentResolver(jwtTokenProvider));
    }
}
