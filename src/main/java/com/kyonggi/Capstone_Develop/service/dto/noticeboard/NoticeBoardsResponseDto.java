package com.kyonggi.Capstone_Develop.service.dto.noticeboard;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class NoticeBoardsResponseDto {
    List<NoticeBoardResponseDto> noticeBoards;
    
    public static NoticeBoardsResponseDto from(List<NoticeBoardResponseDto> noticeBoards) {
        return new NoticeBoardsResponseDto(noticeBoards);
    }
}
