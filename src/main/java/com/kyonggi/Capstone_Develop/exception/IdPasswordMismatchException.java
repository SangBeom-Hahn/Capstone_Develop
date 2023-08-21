package com.kyonggi.Capstone_Develop.exception;

import org.springframework.http.HttpStatus;

public class IdPasswordMismatchException extends CspopException{
    public IdPasswordMismatchException() {
        super(
                "아이디 혹은 비밀번호를 확인해주세요.",
                "아이디 혹은 비밀번호를 확인해주세요.",
                HttpStatus.BAD_REQUEST,
                "3"
        );
    }
}
