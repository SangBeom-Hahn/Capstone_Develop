package com.kyonggi.Capstone_Develop.repository;

import com.kyonggi.Capstone_Develop.domain.Comment;
import com.kyonggi.Capstone_Develop.domain.NoticeBoard;
import com.kyonggi.Capstone_Develop.domain.student.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class CommentRepositoryTest extends RepositoryTest{
    private Comment dummyComment;
    
    private NoticeBoard dummyNoticeBoard;
    
    private Student dummyStudent;
    
    @BeforeEach
    void setUp() {
        dummyStudent = new Student(
                "cherry1",
                "dummyPassword",
                LocalDate.of(2023, 07, 18),
                "컴퓨터공학부",
                Grade.FOURTH,
                PhoneNumber.from("010-1111-1111"),
                Sex.FEMALE,
                "한상범",
                Email.from("1@naver.com"),
                "20182222",
                RoleType.STUDENT
        );
        
        dummyNoticeBoard = new NoticeBoard(
                "공지사항",
                false,
                "제목",
                1,
                dummyStudent
        );
    
        dummyComment = new Comment(
                dummyNoticeBoard,
                dummyStudent,
                "content"
        );
    
        studentRepository.save(dummyStudent);
        noticeBoardRepository.save(dummyNoticeBoard);
    }

    @Test
    @DisplayName("댓글을 등록한다.")
    void save() {
        // when
        Comment saveComment = commentRepository.save(dummyComment);
    
        // then
        assertAll(
                () -> assertThat(saveComment.getId()).isNotNull(),
                () -> assertThat(saveComment).isEqualTo(dummyComment)
        );
    }
    
    @Test
    @DisplayName("id로 댓글을 찾는다.")
    void findById() {
        // given
        Long commentId = commentRepository.save(dummyComment)
                .getId();
    
        // when
        Comment findComment = commentRepository.findById(commentId)
                .orElseThrow();
    
        // then
        assertThat(findComment)
                .extracting("id", "content")
                .containsExactly(commentId, "content");
    }
    
    @Test
    @DisplayName("id로 찾은 댓글을 수정한다.")
    void updateComment() {
        // given
        Long commentId = commentRepository.save(dummyComment)
                .getId();
        String changeContent = "changeContent";
    
        // when
        dummyComment.changeContent(changeContent);
        Comment findComment = commentRepository.findById(commentId)
                .orElseThrow();
    
        // then
        assertThat(findComment.getContent())
                .isEqualTo(changeContent);
    }
    
    @Test
    @DisplayName("댓글을 삭제한다.")
    void deleteComment() {
        // given
        Long commentId = commentRepository.save(dummyComment)
                .getId();
    
        // when
        commentRepository.deleteById(commentId);
      
        // then
        assertThat(commentRepository.findById(commentId))
                .isEmpty();
    }
}