package com.kyonggi.Capstone_Develop.service.dto.graduation;

import com.kyonggi.Capstone_Develop.domain.graduation.Method;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GraduationSaveRequestDto {
    private Method method;
}
