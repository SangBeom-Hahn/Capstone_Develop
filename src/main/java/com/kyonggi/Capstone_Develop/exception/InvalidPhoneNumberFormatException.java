package com.kyonggi.Capstone_Develop.exception;

import org.springframework.http.HttpStatus;

public class InvalidPhoneNumberFormatException extends CspopException{
    public InvalidPhoneNumberFormatException(final String phoneNumber) {
        super(
                String.format("올바르지 않은 전화번호 형식입니다. phoneNumber={%s}", phoneNumber),
                "올바르지 않은 전화번호 형식입니다.",
                HttpStatus.BAD_REQUEST,
                "11"
        );
    }
}
