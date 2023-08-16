package com.kyonggi.Capstone_Develop.exception;

import org.springframework.http.HttpStatus;

public class TokenInvalidSecretKeyException extends CspopException{
    public TokenInvalidSecretKeyException(final String token) {
        super(
                String.format("토큰의 secret key가 변조됐습니다. 해킹의 우려가 존재합니다. token={%s}", token),
                "토큰의 secret key가 변조됐습니다. 해킹의 우려가 존재합니다.",
                HttpStatus.UNAUTHORIZED,
                "5"
        );
    }
}
