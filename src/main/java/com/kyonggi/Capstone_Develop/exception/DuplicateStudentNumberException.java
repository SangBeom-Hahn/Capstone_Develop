package com.kyonggi.Capstone_Develop.exception;

import org.springframework.http.HttpStatus;

public class DuplicateStudentNumberException extends CspopException{
    public DuplicateStudentNumberException(final String studentNumber) {
        super(
                String.format("이미 존재하는 학번입니다. studentNumber={%s}", studentNumber),
                "이미 존재하는 학번입니다.",
                HttpStatus.BAD_REQUEST,
                "2"
        );
    }
}
