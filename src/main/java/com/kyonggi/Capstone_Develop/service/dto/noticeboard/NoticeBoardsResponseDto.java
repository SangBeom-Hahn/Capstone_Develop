package com.kyonggi.Capstone_Develop.service.dto.noticeboard;

import com.kyonggi.Capstone_Develop.controller.dto.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class NoticeBoardsResponseDto {
    private List<AllNoticeBoardResponseDto> noticeBoards;
    
    private PageInfo pageInfo;
    
    public static NoticeBoardsResponseDto of(final List<AllNoticeBoardResponseDto> noticeBoards, final PageInfo pageInfo) {
        return new NoticeBoardsResponseDto(noticeBoards, pageInfo);
    }
}
