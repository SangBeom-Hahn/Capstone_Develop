package com.kyonggi.Capstone_Develop.exception;

import org.springframework.http.HttpStatus;

public class RefreshTokenInvalidException extends CspopException {

    public RefreshTokenInvalidException() {
        super(
                "올바르지 않은 리프레시 토큰입니다.",
                "올바르지 않은 리프레시 토큰입니다.",
                HttpStatus.UNAUTHORIZED,
                "22"
        );
    }
}
