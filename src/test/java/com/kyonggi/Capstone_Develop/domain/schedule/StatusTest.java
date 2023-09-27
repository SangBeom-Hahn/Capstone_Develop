package com.kyonggi.Capstone_Develop.domain.schedule;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.kyonggi.Capstone_Develop.domain.schedule.Status.*;
import static java.time.LocalDate.now;
import static java.time.LocalDate.of;
import static org.assertj.core.api.Assertions.assertThat;

class StatusTest {
    @Test
    @DisplayName("현재 시간이 시작 시간보다 이르면 대기 상태이다.")
    void waitTest() {
        // given
        LocalDate startDate = of(2024, 9, 17);
        LocalDate endDate = of(2024, 9, 20);
        
        // then
        assertThat(Status.of(now(), startDate, endDate))
                .isEqualTo(WAIT);
    }
    
    @Test
    @DisplayName("현재 시간이 종료 시간보다 느리면 마감 상태이다.")
    void endTest() {
        // given
        LocalDate startDate = of(2023, 7, 17);
        LocalDate endDate = of(2023, 7, 20);
        
        // then
        assertThat(Status.of(now(), startDate, endDate))
                .isEqualTo(END);
    }
    
    @Test
    @DisplayName("현재 시간이 시작 시간보다 느리고 종료 시간보다 빠르면 진행 상태이다.")
    void proceedingTest() {
        // given
        LocalDate startDate = of(2023, 8, 17);
        LocalDate endDate = LocalDate.MAX;
        
        // then
        assertThat(Status.of(now(), startDate, endDate))
                .isEqualTo(PROCEEDING);
    }
}