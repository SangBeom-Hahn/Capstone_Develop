package com.kyonggi.Capstone_Develop.exception;

import org.springframework.http.HttpStatus;

public class DuplicateApplyException extends CspopException{
    public DuplicateApplyException(final String loginId) {
        super(
                String.format("동일 단계의 졸업 신청입니다. loginId={%s}", loginId),
                "동일 단계의 졸업 신청입니다.",
                HttpStatus.BAD_REQUEST,
                "18"
        );
    }
}
