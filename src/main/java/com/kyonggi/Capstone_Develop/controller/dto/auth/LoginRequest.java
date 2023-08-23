package com.kyonggi.Capstone_Develop.controller.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.kyonggi.Capstone_Develop.controller.dto.ValidateMessage.EMPTY_MESSAGE;
import static com.kyonggi.Capstone_Develop.controller.dto.ValidateMessage.PASSWORD_FORMAT;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @NotBlank(message = EMPTY_MESSAGE)
    private String loginId;
    
    @NotBlank(message = EMPTY_MESSAGE)
    @Pattern(regexp = PASSWORD_FORMAT)
    private String loginPassword;
}
