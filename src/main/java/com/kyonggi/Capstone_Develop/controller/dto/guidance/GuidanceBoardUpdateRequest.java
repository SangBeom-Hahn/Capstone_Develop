package com.kyonggi.Capstone_Develop.controller.dto.guidance;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GuidanceBoardUpdateRequest {
    @NotBlank
    private String content;
}
