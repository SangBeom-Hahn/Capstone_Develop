package com.kyonggi.Capstone_Develop.exception;

import org.springframework.http.HttpStatus;

public class InvalidTokenException extends CspopException{
    public InvalidTokenException() {
        super(
                "로그인이 필요한 서비스입니다.",
                "로그인이 필요한 서비스입니다.",
                HttpStatus.UNAUTHORIZED,
                "8"
        );
    }
}
