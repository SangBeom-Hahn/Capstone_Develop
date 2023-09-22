package com.kyonggi.Capstone_Develop.service.dto.noticeboard;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NoticeBoardUpdateRequestDto {
    private String content;
    
    private String title;
}
