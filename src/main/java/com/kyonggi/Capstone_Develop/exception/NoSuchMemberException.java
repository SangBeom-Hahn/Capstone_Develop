package com.kyonggi.Capstone_Develop.exception;

import org.springframework.http.HttpStatus;

public class NoSuchMemberException extends CspopException{
    public NoSuchMemberException(final Long memberId) {
        super(
                String.format("가입되지 않은 학생입니다. memberId={%d}", memberId),
                "가입되지 않은 학생입니다.",
                HttpStatus.NOT_FOUND,
                "10"
        );
    }
}
