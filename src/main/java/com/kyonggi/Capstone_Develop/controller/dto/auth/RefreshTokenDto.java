package com.kyonggi.Capstone_Develop.controller.dto.auth;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class RefreshTokenDto {

    private String refreshToken;
}
