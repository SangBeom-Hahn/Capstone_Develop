package com.kyonggi.Capstone_Develop.exception;

import org.springframework.http.HttpStatus;

public class DuplicateSubmitException extends CspopException{
    public DuplicateSubmitException(final String loginId) {
        super(
                String.format("이미 신청 접수 되었습니다. loginId={%s}", loginId),
                "이미 신청 접수 되었습니다.",
                HttpStatus.BAD_REQUEST,
                "20"
        );
    }
}
