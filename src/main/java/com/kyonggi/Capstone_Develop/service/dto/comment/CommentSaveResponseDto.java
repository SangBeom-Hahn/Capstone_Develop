package com.kyonggi.Capstone_Develop.service.dto.comment;

import com.kyonggi.Capstone_Develop.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentSaveResponseDto {
    private Long id;
    
    public static CommentSaveResponseDto from(Comment comment) {
        return new CommentSaveResponseDto(comment.getId());
    }
}
