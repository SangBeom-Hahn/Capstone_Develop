package com.kyonggi.Capstone_Develop.exception;

import org.springframework.http.HttpStatus;

public class DuplicateLoginIdException extends CspopException {
    public DuplicateLoginIdException(final String loginId) {
        super(
                String.format("이미 존재하는 아이디입니다. loginId={%s}", loginId),
                "이미 존재하는 아이디입니다.",
                HttpStatus.BAD_REQUEST,
                "1"
        );
    }
}
