package com.kyonggi.Capstone_Develop.repository;

import com.kyonggi.Capstone_Develop.config.JpaAuditingConfig;
import com.kyonggi.Capstone_Develop.domain.schedule.Schedule;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static com.kyonggi.Capstone_Develop.domain.schedule.Status.PROCEEDING;
import static com.kyonggi.Capstone_Develop.domain.schedule.Step.RECEIVED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@Import(JpaAuditingConfig.class)
@Transactional
@DataJpaTest
@RequiredArgsConstructor
class ScheduleRepositoryTest {
    private Schedule schedule;
    
    @Autowired
    private ScheduleRepository scheduleRepository;
    
    @BeforeEach
    void setUp() {
        schedule = new Schedule(
                RECEIVED,
                PROCEEDING,
                LocalDate.of(2023, 3, 7),
                LocalDate.of(2023, 3, 15)
        );
    }
    
    @Test
    @DisplayName("진행 일정을 저장한다.")
    void save() {
        // given
        Schedule saveSchedule = scheduleRepository.save(schedule);
      
        // then
        assertAll(
                () -> assertThat(saveSchedule.getId()).isNotNull(),
                () -> assertThat(saveSchedule).isEqualTo(schedule)
        );
    }
    
    @Test
    @DisplayName("진행 일정을 시작 날짜를 수정한다.")
    void updateStartDate() {
        // given
        Long scheduleId = scheduleRepository.save(schedule)
                .getId();
        LocalDate changeDate = LocalDate.of(2023, 3, 17);
    
        // when
        schedule.changeStartDate(changeDate);
        Schedule findSchedule = scheduleRepository.findById(scheduleId)
                .orElseThrow();
    
        // then
        assertThat(findSchedule.getStartDate())
                .isEqualTo(changeDate);
    }
    
    @Test
    @DisplayName("진행 일정을 종료 날짜를 수정한다.")
    void updateEndDate() {
        // given
        Long scheduleId = scheduleRepository.save(schedule)
                .getId();
        LocalDate changeDate = LocalDate.of(2023, 3, 17);
        
        // when
        schedule.changeEndDate(changeDate);
        Schedule findSchedule = scheduleRepository.findById(scheduleId)
                .orElseThrow();
        
        // then
        assertThat(findSchedule.getEndDate())
                .isEqualTo(changeDate);
    }
}