package com.kyonggi.Capstone_Develop.exception;

import org.springframework.http.HttpStatus;

public class InvalidFileExtensionException extends CspopException{
    public InvalidFileExtensionException(final String extension) {
        super(
                String.format("제공하지 않는 파일 유형입니다. extension={%s}", extension),
                "제공하지 않는 파일 유형입니다.",
                HttpStatus.BAD_REQUEST,
                "23"
        );
    }
}
