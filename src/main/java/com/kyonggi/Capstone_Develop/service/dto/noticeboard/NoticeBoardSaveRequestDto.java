package com.kyonggi.Capstone_Develop.service.dto.noticeboard;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NoticeBoardSaveRequestDto {
    private String content;
    
    private boolean fix;
    
    private String title;
    
    private Integer views;
}
