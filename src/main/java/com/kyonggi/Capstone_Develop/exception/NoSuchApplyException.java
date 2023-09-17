package com.kyonggi.Capstone_Develop.exception;

import org.springframework.http.HttpStatus;

public class NoSuchApplyException extends CspopException{
    public NoSuchApplyException() {
        super(
                "졸업을 신청하지 않은 학생입니다.",
                "졸업을 신청하지 않은 학생입니다.",
                HttpStatus.NOT_FOUND,
                "19"
        );
    }
}
