package com.kyonggi.Capstone_Develop.domain;

import com.kyonggi.Capstone_Develop.domain.noticeboard.NoticeBoard;
import com.kyonggi.Capstone_Develop.domain.student.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class NoticeBoardTest {
    @Test
    @DisplayName("공지사항을 생성한다.")
    void construct() {
        // given
        Student student = new Student(
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
        
        // then
        Assertions.assertDoesNotThrow(() -> new NoticeBoard(
                "content",
                false,
                "제목",
                1,
                student
        ));
    }
    
    @Test
    @DisplayName("공지사항 내용을 변경한다.")
    void changeContent() {
        // given
        Student student = new Student(
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
        
        NoticeBoard noticeBoard = new NoticeBoard(
                "content",
                false,
                "제목",
                1,
                student
        );
        String changeContent = "changeContent";
    
        // when
        noticeBoard.changeContent(changeContent);
      
        // then
        assertThat(noticeBoard.getContent()).isEqualTo(changeContent);
    }
    
    @Test
    @DisplayName("공지사항 고정 여부를 변경한다.")
    void changeFix() {
        // given
        Student student = new Student(
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
        
        NoticeBoard noticeBoard = new NoticeBoard(
                "content",
                false,
                "제목",
                1,
                student
        );
        boolean changeFix = true;
        
        // when
        noticeBoard.changeFix(changeFix);
        
        // then
        assertThat(noticeBoard.getFix()).isEqualTo(changeFix);
    }
    
    @Test
    @DisplayName("공지사항 제목을 변경한다.")
    void changeTitle() {
        // given
        Student student = new Student(
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
        
        NoticeBoard noticeBoard = new NoticeBoard(
                "content",
                false,
                "제목",
                1,
                student
        );
        String changeTitle = "changeTitle";
        
        // when
        noticeBoard.changeTitle(changeTitle);
        
        // then
        assertThat(noticeBoard.getTitle())
                .isEqualTo(changeTitle);
    }
    
    @Test
    @DisplayName("공지사항에 댓글을 추가한다.")
    void addComment() {
        // given
        Student student = new Student(
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
    
        NoticeBoard noticeBoard = new NoticeBoard(
                "content",
                false,
                "제목",
                1,
                student
        );
    
        Comment comment1 = new Comment(noticeBoard, student, "내용1");
        Comment comment2 = new Comment(noticeBoard, student, "내용2");
    
        List<Comment> expected = List.of(
                comment1,
                comment2
        );
      
        // then
        assertThat(noticeBoard.getComments())
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }
}