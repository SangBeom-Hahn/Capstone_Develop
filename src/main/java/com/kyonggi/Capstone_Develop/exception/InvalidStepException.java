package com.kyonggi.Capstone_Develop.exception;

import org.springframework.http.HttpStatus;

public class InvalidStepException extends CspopException{
    public InvalidStepException(final String loginId) {
        super(
                String.format("올바르지 않은 단계의 신청입니다. loginId={%s}", loginId),
                "올바르지 않은 단계의 신청입니다.",
                HttpStatus.BAD_REQUEST,
                "21"
        );
    }
}
