package com.kyonggi.Capstone_Develop.service.dto.noticeboard;

import com.kyonggi.Capstone_Develop.domain.NoticeBoard;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NoticeBoardSaveResponseDto {
    private Long id;
    
    public static NoticeBoardSaveResponseDto from(NoticeBoard noticeBoard) {
        return new NoticeBoardSaveResponseDto(noticeBoard.getId());
    }
}
