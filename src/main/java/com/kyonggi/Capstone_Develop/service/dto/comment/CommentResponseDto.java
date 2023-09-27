package com.kyonggi.Capstone_Develop.service.dto.comment;

import com.kyonggi.Capstone_Develop.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentResponseDto {
    private Long id;
    
    private String content;
    
    private String studentLoginId;
    
    public static CommentResponseDto from(Comment comment) {
        return new CommentResponseDto(comment.getId(), comment.getContent(), comment.getStudentLoginId());
    }
}
