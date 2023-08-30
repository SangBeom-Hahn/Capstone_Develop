package com.kyonggi.Capstone_Develop.service.dto;

import com.kyonggi.Capstone_Develop.domain.GuidanceBoard;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@AllArgsConstructor
public class GuidanceBoardResponseDto {
    private Long id;
    
    private String content;
    
    public static GuidanceBoardResponseDto from(GuidanceBoard guidanceBoard) {
        return new GuidanceBoardResponseDto(guidanceBoard.getId(), guidanceBoard.getContent());
    }
}
