package com.kyonggi.Capstone_Develop.controller.dto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@ToString
public class ErrorResponse {
    private String errorCode;
    private String message;
}
