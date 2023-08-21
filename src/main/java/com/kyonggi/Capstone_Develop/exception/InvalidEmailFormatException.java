package com.kyonggi.Capstone_Develop.exception;

import org.springframework.http.HttpStatus;

public class InvalidEmailFormatException extends CspopException{
    public InvalidEmailFormatException(String email) {
        super(
                String.format("올바르지 않은 이메일 형식입니다. email={%s}", email),
                "올바르지 않은 이메일 형식입니다.",
                HttpStatus.BAD_REQUEST,
                "9"
        );
    }
}
