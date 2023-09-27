package com.kyonggi.Capstone_Develop.service.dto.noticeboard;

import com.kyonggi.Capstone_Develop.domain.NoticeBoard;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class AllNoticeBoardResponseDto {
    private Long id;
    
    private String title;
    
    private String authorLoginId;
    
    private LocalDateTime createDate;
    
    private Integer views;
    
    public static AllNoticeBoardResponseDto from(NoticeBoard noticeBoard) {
        return new AllNoticeBoardResponseDto(
                noticeBoard.getId(),
                noticeBoard.getTitle(),
                noticeBoard.getAuthorLoginId(),
                noticeBoard.getCreatedDate(),
                noticeBoard.getViews()
        );
    }
}
