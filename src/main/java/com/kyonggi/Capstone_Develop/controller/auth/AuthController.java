package com.kyonggi.Capstone_Develop.controller.auth;

import com.kyonggi.Capstone_Develop.controller.dto.auth.LoginRequest;
import com.kyonggi.Capstone_Develop.controller.dto.auth.LogoutRequest;
import com.kyonggi.Capstone_Develop.controller.dto.auth.RefreshTokenDto;
import com.kyonggi.Capstone_Develop.service.AuthService;
import com.kyonggi.Capstone_Develop.service.dto.auth.AccessTokenResponseDto;
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
    public ResponseEntity<Void> logout(@RequestBody @Valid final LogoutRequest logoutRequest) {
        authService.logout(logoutRequest.getAccessToken(), logoutRequest.getRefreshToken());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/renewal-token")
    public ResponseEntity<AccessTokenResponseDto> renewalToken(@RequestBody @Valid RefreshTokenDto refreshTokenDto) {
        AccessTokenResponseDto accessTokenResponseDto =
                authService.renewalToken(refreshTokenDto.getRefreshToken());

        return ResponseEntity.ok(accessTokenResponseDto);
    }
}
