package com.kyonggi.Capstone_Develop.controller.auth;

import com.kyonggi.Capstone_Develop.domain.student.RoleType;
import com.kyonggi.Capstone_Develop.exception.InvalidRoleTypeException;
import com.kyonggi.Capstone_Develop.support.AuthorizationExtractor;
import com.kyonggi.Capstone_Develop.support.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class AdminInterceptor implements HandlerInterceptor {
    private final JwtTokenProvider jwtTokenProvider;
    
    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response,
                             final Object handler) throws Exception {
        final String token = AuthorizationExtractor.extract(request);
        String rolePayload = jwtTokenProvider.getRolePayload(token);
        
        validateRoleType(rolePayload);
    
        return true;
    }
    
    private static void validateRoleType(final String rolePayload) {
        if (RoleType.ADMIN != RoleType.valueOf(rolePayload)) {
            throw new InvalidRoleTypeException();
        }
    }
}
