package com.kyonggi.Capstone_Develop.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GuidanceBoardTest {
    @Test
    @DisplayName("안내 및 내규를 생성한다.")
    void construct() {
        assertDoesNotThrow(() -> new GuidanceBoard("content"));
    }
    
    @Test
    @DisplayName("안내 및 내규 게시판 내용을 수정한다.")
    void GuidanceBoardTest() {
        // given
        GuidanceBoard guidanceBoard = new GuidanceBoard("content");
        String expectedContent = "expectedContent";
    
        // when
        guidanceBoard.changeContent(expectedContent);
        
        // then
        assertThat(guidanceBoard.getContent())
                .isEqualTo(expectedContent);
    }
}