package com.kyonggi.Capstone_Develop.service;

import com.kyonggi.Capstone_Develop.domain.Comment;
import com.kyonggi.Capstone_Develop.exception.NotAuthorException;
import com.kyonggi.Capstone_Develop.exception.NotFoundCommentException;
import com.kyonggi.Capstone_Develop.repository.CommentRepository;
import com.kyonggi.Capstone_Develop.service.dto.comment.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    
    public CommentResponseDto findComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundCommentException(id));
    
        return CommentResponseDto.from(comment);
    }
    
    public void updateComment(Long id, String content) {
        Comment findComment = commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundCommentException(id));
        
        findComment.changeContent(content);
    }
    
    public void deleteComment(Long commentId, Long authorId) {
        Comment findComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundCommentException(commentId));
        
        validateAuthor(authorId, findComment);
        commentRepository.deleteById(commentId);
    }
    
    private void validateAuthor(Long authorID, Comment comment) {
        if (!comment.isStudent(authorID)) {
            throw new NotAuthorException(authorID);
        }
    }
}
