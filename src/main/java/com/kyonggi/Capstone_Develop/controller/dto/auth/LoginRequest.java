package com.kyonggi.Capstone_Develop.controller.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @NotBlank(message = "비어있는 항목을 입력해주세요.")
    private String loginId;
    
    @NotNull(message = "비어있는 항목을 입력해주세요.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&.])[A-Za-z[0-9]$@$!%*#?&.]{" + 2 + "," + 16 + "}$")
    private String loginPassword;
}
