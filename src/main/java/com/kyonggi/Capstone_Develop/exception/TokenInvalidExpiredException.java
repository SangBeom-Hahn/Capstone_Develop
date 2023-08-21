package com.kyonggi.Capstone_Develop.exception;

import org.springframework.http.HttpStatus;

public class TokenInvalidExpiredException extends CspopException{
    public TokenInvalidExpiredException() {
        super(
                "토큰의 유효기간이 만료됐습니다.",
                "토큰의 유효기간이 만료됐습니다.",
                HttpStatus.UNAUTHORIZED,
                "6"
        );
    }
}
