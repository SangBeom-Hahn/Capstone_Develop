package com.kyonggi.Capstone_Develop.domain;

import com.kyonggi.Capstone_Develop.domain.student.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class CommentTest {
    @Test
    @DisplayName("댓글을 생성한다.")
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
        
        NoticeBoard noticeBoard = new NoticeBoard(
                "공지사항",
                false,
                "제목",
                1,
                student
        );
        
        // then
        assertDoesNotThrow(() ->
                new Comment(noticeBoard, student, "content"));
    }
}