package com.kyonggi.Capstone_Develop.service.dto.auth;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccessTokenResponseDto {
    private String accessToken;

    public static AccessTokenResponseDto from(String accessToken) {
        return new AccessTokenResponseDto(accessToken);
    }
}
