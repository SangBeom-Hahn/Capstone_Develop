package com.kyonggi.Capstone_Develop.config;

import com.kyonggi.Capstone_Develop.controller.auth.AdminInterceptor;
import com.kyonggi.Capstone_Develop.controller.auth.AuthenticationPrincipalArgumentResolver;
import com.kyonggi.Capstone_Develop.controller.auth.LoginInterceptor;
import com.kyonggi.Capstone_Develop.repository.StudentRepository;
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
    private final StudentRepository studentRepository;
    
    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor(jwtTokenProvider))
                .order(1)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/login/auth")
                .excludePathPatterns("/api/logout")
                .excludePathPatterns("/api/students");
    
        registry.addInterceptor(new AdminInterceptor(jwtTokenProvider))
                .order(2)
                .addPathPatterns("/api/admins/**");
    }
    
    @Override
    public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new AuthenticationPrincipalArgumentResolver(jwtTokenProvider));
    }
}
