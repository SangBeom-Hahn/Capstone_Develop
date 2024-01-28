package com.kyonggi.Capstone_Develop.service;

import com.kyonggi.Capstone_Develop.domain.Comment;
import com.kyonggi.Capstone_Develop.domain.noticeboard.NoticeBoard;
import com.kyonggi.Capstone_Develop.domain.student.Student;
import com.kyonggi.Capstone_Develop.exception.NoSuchMemberException;
import com.kyonggi.Capstone_Develop.exception.NotAuthorException;
import com.kyonggi.Capstone_Develop.exception.NotFoundCommentException;
import com.kyonggi.Capstone_Develop.exception.NotFoundNoticeBoardException;
import com.kyonggi.Capstone_Develop.repository.CommentRepository;
import com.kyonggi.Capstone_Develop.repository.StudentRepository;
import com.kyonggi.Capstone_Develop.repository.noticeboard.NoticeBoardRepository;
import com.kyonggi.Capstone_Develop.service.dto.comment.CommentResponseDto;
import com.kyonggi.Capstone_Develop.service.dto.comment.CommentSaveResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    
    private final NoticeBoardRepository noticeBoardRepository;
    
    private final StudentRepository studentRepository;
    
    public CommentSaveResponseDto save(Long noticeBoardId, Long studentId, String content) {
        NoticeBoard noticeBoard = noticeBoardRepository.findById(noticeBoardId)
                .orElseThrow(() -> new NotFoundNoticeBoardException(noticeBoardId));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchMemberException(studentId));
    
        Comment comment = new Comment(noticeBoard, student, content);
    
        Comment saveComment = commentRepository.save(comment);
        return CommentSaveResponseDto.from(saveComment);
    }
    
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
