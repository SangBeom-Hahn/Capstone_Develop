package com.kyonggi.Capstone_Develop.service;

import com.kyonggi.Capstone_Develop.controller.dto.comment.CommentSaveRequest;
import com.kyonggi.Capstone_Develop.domain.Comment;
import com.kyonggi.Capstone_Develop.domain.noticeboard.NoticeBoard;
import com.kyonggi.Capstone_Develop.domain.student.*;
import com.kyonggi.Capstone_Develop.exception.NotAuthorException;
import com.kyonggi.Capstone_Develop.exception.NotFoundCommentException;
import com.kyonggi.Capstone_Develop.service.dto.comment.CommentResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CommentServiceTest extends ServiceTest{
    private Student student;
    
    private NoticeBoard noticeBoard1;
    
    private Comment comment;
    
    @BeforeEach
    void setUp() {
        student = new Student(
                "201812709",
                "dummyPassword",
                LocalDate.of(2023, 07, 18),
                "컴퓨터공학부",
                PhoneNumber.from("010-1111-1111"),
                Sex.MALE,
                "한상범",
                Email.from("1@naver.com"),
                RoleType.STUDENT,
                "answerPW",
                Classification.from("UNDERGRADUATE_STUDENT")
        );
        
        noticeBoard1 = new NoticeBoard("content", false, "title", 0, student);
        
        comment = new Comment(noticeBoard1, student, "내용1");
        
        studentRepository.save(student);
        noticeBoardRepository.save(noticeBoard1);
        commentRepository.save(comment);
    }
    
    @Test
    @DisplayName("댓글을 저장하고 id로 조회한다.")
    void saveCommentAndFindById() {
        // given
        CommentSaveRequest content = new CommentSaveRequest("content");
        Long saveCommentId = commentService.save(
                noticeBoard1.getId(),
                student.getId(),
                content.getContent()
        ).getId();
    
        // when
        CommentResponseDto commentResponseDto =
                commentService.findComment(saveCommentId);
    
        // then
        assertThat(commentResponseDto).extracting("id", "content", "studentLoginId")
                .containsExactly(saveCommentId, "content", student.getLoginId());
    }
    
    @Test
    @DisplayName("댓글을 수정한다.")
    void updateComment() {
        // given
        String expectedComment = "expectedComment";
        Long changeCommentId = comment.getId();
    
        // when
        commentService.updateComment(changeCommentId, expectedComment);
        Comment actualComment = commentRepository.findById(changeCommentId)
                .orElseThrow();
        
        // then
        assertThat(actualComment.getContent())
                .isEqualTo(expectedComment);
    }
    
    @Test
    @DisplayName("댓글을 id로 삭제한다.")
    void deleteComment() {
        // given
        Long deleteCommentId = comment.getId();
        
        // when
        commentService.deleteComment(deleteCommentId, student.getId());
        
        // then
        assertThatThrownBy(() -> commentService.findComment(deleteCommentId))
                .isInstanceOf(NotFoundCommentException.class)
                .hasMessage(String.format("해당 댓글이 존재하지 않습니다. id={%d}", deleteCommentId));
    }
    
    @Test
    @DisplayName("댓글 작성자가 아닌 사람이 댓글을 삭제하면 예외가 발생한다.")
    void throwException_invalidAuthor() {
        // given
        Long deleteCommentId = comment.getId();
        long invalidAuthorId = 999L;
        
        // then
        assertThatThrownBy(() -> commentService.deleteComment(deleteCommentId, invalidAuthorId))
                .isInstanceOf(NotAuthorException.class)
                .hasMessage(String.format("해당 회원은 작성자가 아닙니다. id={%d}", invalidAuthorId));
    }
}