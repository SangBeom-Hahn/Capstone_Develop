package com.kyonggi.Capstone_Develop.controller.dto.graduation;

import com.kyonggi.Capstone_Develop.domain.graduation.Method;
import com.kyonggi.Capstone_Develop.service.dto.graduation.GraduationSaveRequestDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class GraduationSaveRequest {
    
    @NotBlank
    private String method;
    
    public GraduationSaveRequestDto toServiceDto() {
        return new GraduationSaveRequestDto(
                Method.from(method)
        );
    }
}
