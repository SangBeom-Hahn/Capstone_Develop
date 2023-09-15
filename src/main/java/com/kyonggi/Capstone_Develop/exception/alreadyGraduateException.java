package com.kyonggi.Capstone_Develop.exception;

import org.springframework.http.HttpStatus;

public class alreadyGraduateException extends CspopException{
    public alreadyGraduateException() {
        super(
                "이미 졸업한 학생입니다.",
                "이미 졸업한 학생입니다.",
                HttpStatus.BAD_REQUEST,
                "18"
        );
    }
}
