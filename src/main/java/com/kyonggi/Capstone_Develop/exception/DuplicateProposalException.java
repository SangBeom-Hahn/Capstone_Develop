package com.kyonggi.Capstone_Develop.exception;

import org.springframework.http.HttpStatus;

public class DuplicateProposalException extends CspopException{
    public DuplicateProposalException(final String loginId) {
        super(
                String.format("이미 제안서 제출 되었습니다. loginId={%s}", loginId),
                "이미 제안서 제출 되었습니다.",
                HttpStatus.BAD_REQUEST,
                "21"
        );
    }
}
