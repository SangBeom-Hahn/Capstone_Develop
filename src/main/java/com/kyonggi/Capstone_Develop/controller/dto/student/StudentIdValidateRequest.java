package com.kyonggi.Capstone_Develop.controller.dto.student;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

import static com.kyonggi.Capstone_Develop.controller.dto.ValidateMessage.EMPTY_MESSAGE;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudentIdValidateRequest {
    @NotBlank(message = EMPTY_MESSAGE)
    private String studentId;
}
