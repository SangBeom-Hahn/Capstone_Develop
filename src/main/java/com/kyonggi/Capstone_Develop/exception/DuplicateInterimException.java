package com.kyonggi.Capstone_Develop.exception;

import org.springframework.http.HttpStatus;

public class DuplicateInterimException extends CspopException{
    public DuplicateInterimException(final String loginId) {
        super(
                String.format("이미 중간보고서 제출 되었습니다. loginId={%s}", loginId),
                "이미 중간보고서 제출 되었습니다.",
                HttpStatus.BAD_REQUEST,
                "22"
        );
    }
}
