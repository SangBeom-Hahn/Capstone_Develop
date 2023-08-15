package com.kyonggi.Capstone_Develop.controller.auth;

import com.kyonggi.Capstone_Develop.controller.dto.auth.LoginMemberRequest;
import com.kyonggi.Capstone_Develop.support.AuthorizationExtractor;
import com.kyonggi.Capstone_Develop.support.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class AuthenticationPrincipalArgumentResolver implements HandlerMethodArgumentResolver {
    
    private final JwtTokenProvider jwtTokenProvider;
    
    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthenticationPrincipal.class);
    }
    
    @Override
    public Object resolveArgument(final MethodParameter parameter, final ModelAndViewContainer mavContainer,
                                  final NativeWebRequest webRequest, final WebDataBinderFactory binderFactory) {
        final HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        final String token = AuthorizationExtractor.extract(request);
        final String id = jwtTokenProvider.getPayload(token);
        
        return new LoginMemberRequest(Long.valueOf(id));
    }
}
