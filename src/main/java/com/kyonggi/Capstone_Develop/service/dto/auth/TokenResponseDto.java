package com.kyonggi.Capstone_Develop.service.dto.auth;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@ToString
public class TokenResponseDto {
    private String accessToken;
    private String refreshToken;
    private Long id;
    
    public static TokenResponseDto of(final String accessToken, final String refreshToken, final Long id) {
        return new TokenResponseDto(accessToken, refreshToken, id);
    }
}
