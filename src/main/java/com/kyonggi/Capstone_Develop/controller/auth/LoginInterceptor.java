package com.kyonggi.Capstone_Develop.controller.auth;

import com.kyonggi.Capstone_Develop.support.AuthorizationExtractor;
import com.kyonggi.Capstone_Develop.support.JwtTokenProvider;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    
    private final JwtTokenProvider jwtTokenProvider;
    
    public LoginInterceptor(final JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }
    
    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response,
                             final Object handler) {
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }
        final String token = AuthorizationExtractor.extract(request);
        jwtTokenProvider.validateAbleToken(token);
        return true;
    }
}
