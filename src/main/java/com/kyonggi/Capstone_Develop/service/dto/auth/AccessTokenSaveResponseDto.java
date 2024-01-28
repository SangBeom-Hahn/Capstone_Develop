package com.kyonggi.Capstone_Develop.service.dto.auth;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccessTokenSaveResponseDto {

    private String accessToken;

    private Long memberId;

    public static AccessTokenSaveResponseDto of(
            String accessToken,
            Long memberId
    ) {
        return new AccessTokenSaveResponseDto(
                accessToken,
                memberId
        );
    }
}
