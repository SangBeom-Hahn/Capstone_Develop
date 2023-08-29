package com.kyonggi.Capstone_Develop.exception;

import org.springframework.http.HttpStatus;

public class NotFoundScheduleException extends CspopException{
    public NotFoundScheduleException(Long id) {
        super(
                String.format("해당 진행 일정이 존재하지 않습니다. id={%d}", id),
                "해당 진행 일정이 존재하지 않습니다.",
                HttpStatus.NOT_FOUND,
                "17"
        );
    }
}
