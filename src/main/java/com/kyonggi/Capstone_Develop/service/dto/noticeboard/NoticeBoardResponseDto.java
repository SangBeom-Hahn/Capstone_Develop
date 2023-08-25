package com.kyonggi.Capstone_Develop.service.dto.noticeboard;

import com.kyonggi.Capstone_Develop.domain.NoticeBoard;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NoticeBoardResponseDto {
    private Long id;
    
    private String content;
    
    private Boolean fix;
    
    private String title;
    
    private Integer views;
    
    private String authorLoginId;
    
    public static NoticeBoardResponseDto from(NoticeBoard noticeBoard) {
        return new NoticeBoardResponseDto(
                noticeBoard.getId(),
                noticeBoard.getContent(),
                noticeBoard.getFix(),
                noticeBoard.getTitle(),
                noticeBoard.getViews(),
                noticeBoard.getAuthorLoginId()
        );
    }
}
