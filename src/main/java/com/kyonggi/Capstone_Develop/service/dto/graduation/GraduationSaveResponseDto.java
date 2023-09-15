package com.kyonggi.Capstone_Develop.service.dto.graduation;

import com.kyonggi.Capstone_Develop.domain.graduation.Graduation;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GraduationSaveResponseDto {
    private Long id;
    
    public static GraduationSaveResponseDto from(Graduation graduation) {
        return new GraduationSaveResponseDto(graduation.getId());
    }
}
