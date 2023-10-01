package com.kyonggi.Capstone_Develop.service.dto.graduation;

import lombok.Getter;

import java.util.List;


@Getter
public class GraduationsResponseDto {
    private List<GraduationResponseDto> graduationResponseDtos;
    
    public GraduationsResponseDto(final List<GraduationResponseDto> graduationResponseDtos) {
        this.graduationResponseDtos = graduationResponseDtos;
    }
    
    public static GraduationsResponseDto from(final List<GraduationResponseDto> graduationResponseDtos) {
        return new GraduationsResponseDto(graduationResponseDtos);
    }
}
