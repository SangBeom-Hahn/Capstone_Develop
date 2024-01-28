package com.kyonggi.Capstone_Develop.exception;

import org.springframework.http.HttpStatus;

public class NotUploadException extends CspopException{
    public NotUploadException() {
        super(
                "파일이 업로드 되지 않았습니다.",
                "파일이 업로드 되지 않았습니다.",
                HttpStatus.BAD_REQUEST,
                "24"
        );
    }
}
