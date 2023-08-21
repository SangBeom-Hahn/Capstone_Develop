package com.kyonggi.Capstone_Develop.exception;

import org.springframework.http.HttpStatus;

public class InvalidRoleTypeException extends CspopException{
    public InvalidRoleTypeException() {
        super(
                "관리자 권한이 없습니다.",
                "관리자 권한이 없습니다.",
                HttpStatus.BAD_REQUEST,
                "12"
        );
    }
}
