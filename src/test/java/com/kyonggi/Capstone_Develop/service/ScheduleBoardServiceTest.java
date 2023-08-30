package com.kyonggi.Capstone_Develop.service;

import com.kyonggi.Capstone_Develop.controller.dto.scheduleboard.ScheduleBoardUpdateRequest;
import com.kyonggi.Capstone_Develop.domain.schedule.ScheduleBoard;
import com.kyonggi.Capstone_Develop.service.dto.schedule.ScheduleBoardResponseDto;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RequiredArgsConstructor
@Transactional
class ScheduleBoardServiceTest {
    @Autowired
    private ScheduleBoardService scheduleBoardService;
    
    private ScheduleBoard expectedScheduleBoard;
    
    @BeforeEach
    void setUp() {
        expectedScheduleBoard = new ScheduleBoard(
                "receive",
                "proposal",
                "middleReport",
                "finalReport",
                "finalPass",
                "otherQualification"
        );
    }
    
    
    @Test
    @DisplayName("저장한 진행일정 게시판 내용을 id로 찾는다.")
    void findScheduleBoard() {
        // given
        ScheduleBoardResponseDto scheduleBoardResponseDtos =
                scheduleBoardService.findScheduleBoard();
      
        // then
        assertThat(scheduleBoardResponseDtos).extracting("receive", "proposal", "middleReport", "finalReport", "finalPass", "otherQualification")
                .containsExactly(
                        expectedScheduleBoard.getReceive(),
                        expectedScheduleBoard.getProposal(),
                        expectedScheduleBoard.getMiddleReport(),
                        expectedScheduleBoard.getFinalReport(),
                        expectedScheduleBoard.getFinalPass(),
                        expectedScheduleBoard.getOtherQualification()
                );
    }
    
    @Test
    @DisplayName("저장한 진행일정 게시판 내용을 수정한다.")
    void updateScheduleBoard() {
        // given
        String expectedReceive = "expectedReceive";
        String expectedProposal = "expectedProposal";
        String expectedMiddleReport = "expectedMiddleReport";
        String expectedFinalReport = "expectedFinalReport";
        String expectedFinalPass = "expectedFinalPass";
        String expectedOtherQualification = "expectedOtherQualification";
        
        ScheduleBoardUpdateRequest scheduleBoardUpdateRequest = new ScheduleBoardUpdateRequest(
                expectedReceive,
                expectedProposal,
                expectedMiddleReport,
                expectedFinalReport,
                expectedFinalPass,
                expectedOtherQualification
        );
    
        // when
        scheduleBoardService.updateScheduleBoard(scheduleBoardUpdateRequest.toServiceDto());
        ScheduleBoardResponseDto scheduleBoardResponseDto =
                scheduleBoardService.findScheduleBoard();
    
        // then
        assertThat(scheduleBoardResponseDto).extracting("receive", "proposal", "middleReport", "finalReport", "finalPass", "otherQualification")
                .containsExactly(
                        expectedReceive,
                        expectedProposal,
                        expectedMiddleReport,
                        expectedFinalReport,
                        expectedFinalPass,
                        expectedOtherQualification
                );
    }
}