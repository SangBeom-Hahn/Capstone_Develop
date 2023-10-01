package com.kyonggi.Capstone_Develop.exception;

import org.springframework.http.HttpStatus;

public class NotFoundCommentException extends CspopException{
    public NotFoundCommentException(final Long id) {
        super(
                String.format("해당 댓글이 존재하지 않습니다. id={%d}", id),
                "해당 댓글이 존재하지 않습니다.",
                HttpStatus.NOT_FOUND,
                "15"
        );
    }
}
