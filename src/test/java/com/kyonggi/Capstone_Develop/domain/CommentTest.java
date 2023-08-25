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