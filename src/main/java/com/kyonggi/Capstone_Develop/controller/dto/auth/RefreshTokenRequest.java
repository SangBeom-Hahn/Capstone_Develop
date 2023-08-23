package com.kyonggi.Capstone_Develop.controller.dto.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

import static com.kyonggi.Capstone_Develop.controller.dto.ValidateMessage.EMPTY_MESSAGE;

@Getter
@NoArgsConstructor
public class RefreshTokenRequest {
    @NotBlank(message = EMPTY_MESSAGE)
    private String refreshToken;
}
