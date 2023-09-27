package com.kyonggi.Capstone_Develop.exception;

import org.springframework.http.HttpStatus;

public class NotFoundGuidanceBoardException extends CspopException{
    public NotFoundGuidanceBoardException(Long id) {
        super(
                String.format("해당 안내 및 내규 게시판 내용이 존재하지 않습니다. id={%d}", id),
                "해당 안내 및 내규 게시판 내용이 존재하지 않습니다.",
                HttpStatus.NOT_FOUND,
                "19"
        );
    }
}
