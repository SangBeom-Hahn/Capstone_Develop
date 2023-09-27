package com.kyonggi.Capstone_Develop.service;

import com.kyonggi.Capstone_Develop.controller.dto.guidance.GuidanceBoardUpdateRequest;
import com.kyonggi.Capstone_Develop.domain.GuidanceBoard;
import com.kyonggi.Capstone_Develop.service.dto.guidance.GuidanceBoardResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class GuidanceBoardServiceTest extends ServiceTest{
    
    private GuidanceBoard guidanceBoard;
    
    @BeforeEach
    void setUp() {
        guidanceBoard = new GuidanceBoard("content");
    }
    
    @Test
    @DisplayName("저장한 안내 및 내규 게시판 내용을 id로 찾는다.")
    void findGuidanceBoard() {
        // given
        GuidanceBoardResponseDto guidanceBoardResponseDto =
                guidanceBoardService.findGuidanceBoard();
        
        // then
        assertThat(guidanceBoardResponseDto).extracting("content")
                .isEqualTo(guidanceBoard.getContent());
    }
    
    @Test
    @DisplayName("저장한 안내 및 내규 게시판 일정을 수정한다.")
    void GuidanceBoardServiceTest() {
        // given
        String expectedContent = "expectedContent";
        GuidanceBoardUpdateRequest guidanceBoardUpdateRequest =
                new GuidanceBoardUpdateRequest(expectedContent);
        
        // when
        guidanceBoardService.updateGuidanceBoard(guidanceBoardUpdateRequest.getContent());
        GuidanceBoardResponseDto guidanceBoardResponseDto = guidanceBoardService.findGuidanceBoard();
        
        // then
        assertThat(guidanceBoardResponseDto).extracting("content")
                .isEqualTo(expectedContent);
    }
}