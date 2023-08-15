package com.kyonggi.Capstone_Develop.service.dto.auth;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@ToString
public class TokenResponseDto {
    private String accessToken;
    private Long id;
    
    public static TokenResponseDto of(final String accessToken, Long id) {
        return new TokenResponseDto(accessToken, id);
    }
}
