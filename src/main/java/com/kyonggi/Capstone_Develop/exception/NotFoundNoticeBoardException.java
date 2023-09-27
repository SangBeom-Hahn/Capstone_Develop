package com.kyonggi.Capstone_Develop.exception;

import org.springframework.http.HttpStatus;

public class NotFoundNoticeBoardException extends CspopException{
    public NotFoundNoticeBoardException(Long id) {
        super(
                String.format("해당 공지사항이 존재하지 않습니다. id={%d}", id),
                "해당 공지사항이 존재하지 않습니다.",
                HttpStatus.NOT_FOUND,
                "13"
        );
    }
}
