package com.kyonggi.Capstone_Develop.exception;

import org.springframework.http.HttpStatus;

public class DuplicateFinalException extends CspopException{
    public DuplicateFinalException(final String loginId) {
        super(
                String.format("이미 최종보고서 제출 되었습니다. loginId={%s}", loginId),
                "이미 최종보고서 제출 되었습니다.",
                HttpStatus.BAD_REQUEST,
                "23"
        );
    }
}
