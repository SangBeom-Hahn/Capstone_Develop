package com.kyonggi.Capstone_Develop.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NoticeBoardTest {
    @Test
    @DisplayName("공지사항을 생성한다.")
    void construct() {
        Assertions.assertDoesNotThrow(() -> new NoticeBoard(
                "content",
                false,
                "제목",
                1
        ));
    }
    
    @Test
    @DisplayName("공지사항 내용을 변경한다.")
    void changeContent() {
        // given
        NoticeBoard noticeBoard = new NoticeBoard(
                "content",
                false,
                "제목",
                1
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
        NoticeBoard noticeBoard = new NoticeBoard(
                "content",
                false,
                "제목",
                1
        );
        boolean changeFix = true;
        
        // when
        noticeBoard.changeFix(changeFix);
        
        // then
        assertThat(noticeBoard.isFix()).isEqualTo(changeFix);
    }
    
    @Test
    @DisplayName("공지사항 제목을 변경한다.")
    void changeTitel() {
        // given
        NoticeBoard noticeBoard = new NoticeBoard(
                "content",
                false,
                "제목",
                1
        );
        String changeTitle = "changeTitle";
        
        // when
        noticeBoard.changeTitle(changeTitle);
        
        // then
        assertThat(noticeBoard.getTitle()).isEqualTo(changeTitle);
    }
}