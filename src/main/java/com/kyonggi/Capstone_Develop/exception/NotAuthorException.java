package com.kyonggi.Capstone_Develop.exception;

import org.springframework.http.HttpStatus;

public class NotAuthorException extends CspopException{
    public NotAuthorException(final Long id) {
        super(
                String.format("해당 회원은 작성자가 아닙니다. id={%d}", id),
                "해당 회원은 작성자가 아닙니다.",
                HttpStatus.FORBIDDEN,
                "14"
        );
    }
}
