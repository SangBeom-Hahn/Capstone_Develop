package com.kyonggi.Capstone_Develop.exception;

import org.springframework.http.HttpStatus;

public class NoSuchFileIdException extends CspopException{
    public NoSuchFileIdException(Long id) {
        super(
                String.format("없는 ID 입니다. 다시 로그인 해주세요. loginId={%d}", id),
                "없는 파일입니다.",
                HttpStatus.BAD_REQUEST,
                "25"
        );
    }
}
