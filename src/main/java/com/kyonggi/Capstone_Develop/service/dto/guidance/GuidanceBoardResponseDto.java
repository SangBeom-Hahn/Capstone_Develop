package com.kyonggi.Capstone_Develop.service.dto.guidance;

import com.kyonggi.Capstone_Develop.domain.GuidanceBoard;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GuidanceBoardResponseDto {
    private Long id;
    
    private String content;
    
    public static GuidanceBoardResponseDto from(GuidanceBoard guidanceBoard) {
        return new GuidanceBoardResponseDto(guidanceBoard.getId(), guidanceBoard.getContent());
    }
}