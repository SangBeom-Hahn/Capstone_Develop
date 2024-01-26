package com.kyonggi.Capstone_Develop.controller.auth;

import com.kyonggi.Capstone_Develop.controller.dto.auth.LoginMemberRequest;
import com.kyonggi.Capstone_Develop.controller.dto.auth.LoginRequest;
import com.kyonggi.Capstone_Develop.service.AuthService;
import com.kyonggi.Capstone_Develop.service.dto.auth.TokenResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    
    @PostMapping("/login/auth")
    public ResponseEntity<TokenResponseDto> login(@RequestBody @Valid final LoginRequest loginRequest) {
        TokenResponseDto tokenResponseDto = authService.login(loginRequest.getLoginId(), loginRequest.getLoginPassword());
        return ResponseEntity.ok(tokenResponseDto);
    }
    
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@AuthenticationPrincipal LoginMemberRequest loginMemberRequest) {
        authService.logout(loginMemberRequest.getId());
        return ResponseEntity.noContent().build();
    }
}
