package com.kyonggi.Capstone_Develop.service.dto.noticeboard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@AllArgsConstructor
public class NoticeBoardSaveRequestDto {
    private String content;
    
    private boolean fix;
    
    private String title;
    
    private Integer views;

    private List<MultipartFile> files;
}
