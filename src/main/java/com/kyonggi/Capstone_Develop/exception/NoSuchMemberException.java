package com.kyonggi.Capstone_Develop.exception;

import org.springframework.http.HttpStatus;

public class NoSuchMemberException extends CspopException{
    public NoSuchMemberException(String loginId) {
        super(
                String.format("없는 ID 입니다. 다시 로그인 해주세요. loginId={%s}", loginId),
                "없는 ID 입니다. 다시 로그인 해주세요.",
                HttpStatus.BAD_REQUEST,
                "4"
        );
    }
}
