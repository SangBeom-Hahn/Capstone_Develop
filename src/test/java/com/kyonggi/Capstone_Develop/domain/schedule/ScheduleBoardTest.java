package com.kyonggi.Capstone_Develop.domain.schedule;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ScheduleBoardTest {
    @Test
    @DisplayName("진행일정 게시판을 생성한다.")
    void construct() {
        assertDoesNotThrow(() -> new ScheduleBoard(
                "receive",
                "proposal",
                "middleReport",
                "finalReport",
                "finalPass",
                "otherQualification"
        ));
    }
    
    @Test
    @DisplayName("진행일정 게시판 내용을 수정한다.")
    void ScheduleBoardTest() {
        // given
        ScheduleBoard scheduleBoard = new ScheduleBoard(
                "receive",
                "proposal",
                "middleReport",
                "finalReport",
                "finalPass",
                "otherQualification"
        );
    
        String expectedReceive = "expectedReceive";
        String expectedProposal = "expectedProposal";
        String expectedMiddleReport = "expectedMiddleReport";
        String expectedFinalReport = "expectedFinalReport";
        String expectedFinalPass = "expectedFinalPass";
        String expectedOtherQualification = "expectedOtherQualification";
        
        // when
        scheduleBoard.changeReceive(expectedReceive);
        scheduleBoard.changeProposal(expectedProposal);
        scheduleBoard.changeMiddleReport(expectedMiddleReport);
        scheduleBoard.changeFinalReport(expectedFinalReport);
        scheduleBoard.changeFinalPass(expectedFinalPass);
        scheduleBoard.changeOtherQualification(expectedOtherQualification);
        
        // then
        assertThat(scheduleBoard).extracting("receive", "proposal", "middleReport", "finalReport", "finalPass", "otherQualification")
                .containsExactly(expectedReceive, expectedProposal, expectedMiddleReport, expectedFinalReport, expectedFinalPass, expectedOtherQualification);
    }
}