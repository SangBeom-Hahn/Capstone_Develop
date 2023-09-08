package com.kyonggi.Capstone_Develop.service;

import com.kyonggi.Capstone_Develop.domain.schedule.Schedule;
import com.kyonggi.Capstone_Develop.domain.schedule.Status;
import com.kyonggi.Capstone_Develop.domain.schedule.Step;
import com.kyonggi.Capstone_Develop.repository.ScheduleRepository;
import com.kyonggi.Capstone_Develop.service.dto.schedule.ScheduleResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class ScheduleServiceTest {
    @Autowired
    private ScheduleService scheduleService;
    
    @Autowired
    private ScheduleRepository scheduleRepository;
    
    private Schedule schedule1;
    private Schedule schedule2;
    private Schedule schedule3;
    private Schedule schedule4;
    private Schedule schedule5;
    private Schedule schedule6;
    
    @BeforeEach
    void setUp() {
        schedule1 = new Schedule(
                Step.RECEIVED,
                Status.PROCEEDING,
                LocalDate.of(2023, 8, 29),
                LocalDate.of(2023, 8, 31)
        );
    
        schedule2 = new Schedule(
                Step.ROPOSAL,
                Status.PROCEEDING,
                LocalDate.of(2023, 8, 29),
                LocalDate.of(2023, 8, 31)
        );
    
        schedule3 = new Schedule(
                Step.INTERIM_REPORT,
                Status.PROCEEDING,
                LocalDate.of(2023, 8, 29),
                LocalDate.of(2023, 8, 31)
        );
    
        schedule4 = new Schedule(
                Step.FINAL_REPORT,
                Status.PROCEEDING,
                LocalDate.of(2023, 8, 29),
                LocalDate.of(2023, 8, 31)
        );
    
        schedule5 = new Schedule(
                Step.FINAL_PASS,
                Status.PROCEEDING,
                LocalDate.of(2023, 8, 29),
                LocalDate.of(2023, 8, 31)
        );
    
        schedule6 = new Schedule(
                Step.OTHER_QUALIFICATIONS,
                Status.PROCEEDING,
                LocalDate.of(2023, 8, 29),
                LocalDate.of(2023, 8, 31)
        );
    }
    
    @Test
    @DisplayName("모든 진행일정을 조회한다.")
    void findAll() {
        // given
        List<ScheduleResponseDto> scheduleResponseDtos = scheduleService.findAllSchedule()
                .getSchedules();
    
        // when
        List<ScheduleResponseDto> expected = List.of(
                ScheduleResponseDto.from(schedule1),
                ScheduleResponseDto.from(schedule2),
                ScheduleResponseDto.from(schedule3),
                ScheduleResponseDto.from(schedule4),
                ScheduleResponseDto.from(schedule5),
                ScheduleResponseDto.from(schedule6)
        );
    
        // then
        assertThat(scheduleResponseDtos).usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expected);
                
    }
    
    @Test
    @DisplayName("저장한 진행일정의 시작날짜와 종료날짜, 상태를 수정한다.")
    void updateSchedule() {
        // given
        LocalDate changeStartDate = LocalDate.of(2023, 3, 8);
        LocalDate changeEndDate = LocalDate.of(2023, 3, 16);
    
        // when
        scheduleService.updateSchedule(1L, changeStartDate, changeEndDate);
        Schedule actualSchedule = scheduleRepository.findById(1L)
                .orElseThrow();
        
        // then
        assertThat(actualSchedule).extracting("startDate", "endDate")
                .containsExactly(changeStartDate, changeEndDate);
    }
}