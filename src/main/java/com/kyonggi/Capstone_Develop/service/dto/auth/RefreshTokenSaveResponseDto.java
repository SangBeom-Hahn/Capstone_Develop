package com.kyonggi.Capstone_Develop.service.dto.auth;

import com.kyonggi.Capstone_Develop.domain.refreshtoken.RefreshToken;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshTokenSaveResponseDto {

    private String tokenValue;

    private Long memberId;

    private LocalDateTime expiredTime;

    public static RefreshTokenSaveResponseDto from(RefreshToken refreshToken) {
        return new RefreshTokenSaveResponseDto(
                refreshToken.getTokenValue(),
                refreshToken.getMemberId(),
                refreshToken.getExpiredTime()
        );
    }
}
