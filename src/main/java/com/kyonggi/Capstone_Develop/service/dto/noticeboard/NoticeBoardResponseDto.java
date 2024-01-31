package com.kyonggi.Capstone_Develop.service.dto.noticeboard;

import com.kyonggi.Capstone_Develop.domain.noticeboard.NoticeBoard;
import com.kyonggi.Capstone_Develop.service.dto.comment.CommentResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class NoticeBoardResponseDto {
    private Long id;
    
    private String content;
    
    private Boolean fix;
    
    private String title;
    
    private Integer views;
    
    private LocalDateTime createdDate;
    
    private String authorLoginId;
    
    private List<CommentResponseDto> comments;

    private List<FileResponseDto> files;
    
    public static NoticeBoardResponseDto of(
            NoticeBoard noticeBoard,
            List<CommentResponseDto> comments,
            List<FileResponseDto> files
    ) {
        return new NoticeBoardResponseDto(
                noticeBoard.getId(),
                noticeBoard.getContent(),
                noticeBoard.getFix(),
                noticeBoard.getTitle(),
                noticeBoard.getViews(),
                noticeBoard.getCreatedDate(),
                noticeBoard.getAuthorLoginId(),
                comments,
                files
        );
    }
}
