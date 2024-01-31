package com.kyonggi.Capstone_Develop.exception;

import org.springframework.http.HttpStatus;

public class DateMismatchException extends CspopException{
    public DateMismatchException() {
        super(
                "시작 날짜가 종료 날짜보다 느립니다.",
                "시작 날짜가 종료 날짜보다 느립니다.",
                HttpStatus.BAD_REQUEST,
                "16"
        );
    }
}
