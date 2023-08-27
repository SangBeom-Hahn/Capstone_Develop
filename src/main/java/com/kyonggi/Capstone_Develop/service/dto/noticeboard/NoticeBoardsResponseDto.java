package com.kyonggi.Capstone_Develop.service.dto.noticeboard;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class NoticeBoardsResponseDto {
    List<AllNoticeBoardResponseDto> noticeBoards;
    
    public static NoticeBoardsResponseDto from(List<AllNoticeBoardResponseDto> noticeBoards) {
        return new NoticeBoardsResponseDto(noticeBoards);
    }
}
