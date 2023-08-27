package com.kyonggi.Capstone_Develop.service.dto.noticeboard;

import com.kyonggi.Capstone_Develop.domain.NoticeBoard;
import com.kyonggi.Capstone_Develop.service.dto.comment.CommentResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class NoticeBoardResponseDto {
    private Long id;
    
    private String content;
    
    private Boolean fix;
    
    private String title;
    
    private Integer views;
    
    private String authorLoginId;
    
    private List<CommentResponseDto> comments;
    
    public static NoticeBoardResponseDto of(NoticeBoard noticeBoard, List<CommentResponseDto> comments) {
        return new NoticeBoardResponseDto(
                noticeBoard.getId(),
                noticeBoard.getContent(),
                noticeBoard.getFix(),
                noticeBoard.getTitle(),
                noticeBoard.getViews(),
                noticeBoard.getAuthorLoginId(),
                comments
        );
    }
}
