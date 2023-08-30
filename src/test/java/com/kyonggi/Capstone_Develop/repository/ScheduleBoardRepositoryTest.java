package com.kyonggi.Capstone_Develop.repository;

import com.kyonggi.Capstone_Develop.config.JpaAuditingConfig;
import com.kyonggi.Capstone_Develop.domain.schedule.ScheduleBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@Import(JpaAuditingConfig.class)
@Transactional
class ScheduleBoardRepositoryTest {
    @Autowired
    private ScheduleBoardRepository scheduleBoardRepository;
    
    private ScheduleBoard scheduleBoard;
    
    @BeforeEach
    void setUp() {
        scheduleBoard = new ScheduleBoard(
                "receive",
                "proposal",
                "middleReport",
                "finalReport",
                "finalPass",
                "otherQualification"
        );
    }
    
    @Test
    @DisplayName("진행일정을 저장한다.")
    void save() {
        // given
        ScheduleBoard saveScheduleBoard = scheduleBoardRepository.save(scheduleBoard);
      
        // then
        assertAll(
                () -> assertThat(saveScheduleBoard.getId()).isNotNull(),
                () -> assertThat(saveScheduleBoard).isEqualTo(scheduleBoard)
        );
    }
    
    @Test
    @DisplayName("진행일정을 id로 찾는다.")
    void findById() {
        // given
        Long scheduleBoardId = scheduleBoardRepository.save(scheduleBoard)
                .getId();
    
        // when
        ScheduleBoard findScheduleBoard = scheduleBoardRepository.findById(scheduleBoardId)
                .orElseThrow();
    
        // then
        assertThat(findScheduleBoard).extracting("receive", "proposal", "middleReport", "finalReport", "finalPass", "otherQualification")
                .containsExactly(
                        scheduleBoard.getReceive(),
                        scheduleBoard.getProposal(),
                        scheduleBoard.getMiddleReport(),
                        scheduleBoard.getFinalReport(),
                        scheduleBoard.getFinalPass(),
                        scheduleBoard.getOtherQualification()
                );
    }
    
    @Test
    @DisplayName("진행일정을 수정한다.")
    void update() {
        // given
        Long scheduleBoardId = scheduleBoardRepository.save(scheduleBoard)
                .getId();
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
    
        ScheduleBoard findScheduleBoard = scheduleBoardRepository.findById(scheduleBoardId)
                .orElseThrow();
        
        // then
        assertThat(findScheduleBoard).isEqualTo(scheduleBoard);
    }
    
    @Test
    @DisplayName("진행일정을 id로 삭제한다.")
    void deleteById() {
        // given
        Long scheduleBoardId = scheduleBoardRepository.save(scheduleBoard)
                .getId();
    
        // when
        scheduleBoardRepository.deleteById(scheduleBoardId);
        
        // then
        assertThat(scheduleBoardRepository.findById(scheduleBoardId))
                .isEmpty();
    }
}