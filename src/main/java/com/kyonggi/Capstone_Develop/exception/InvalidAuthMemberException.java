package com.kyonggi.Capstone_Develop.exception;

import org.springframework.http.HttpStatus;

public class InvalidAuthMemberException extends CspopException {
    public InvalidAuthMemberException(final String memberId) {
        super(
                String.format("로그인하지 않은 사용자 접근입니다. 로그인해주세요. memberId={%s}", memberId),
                "로그인하지 않은 사용자 접근입니다. 로그인해주세요.",
                HttpStatus.NOT_ACCEPTABLE,
                "21"
        );
    }
}