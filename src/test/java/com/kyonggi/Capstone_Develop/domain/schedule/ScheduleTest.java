package com.kyonggi.Capstone_Develop.domain.schedule;

import com.kyonggi.Capstone_Develop.exception.DateMismatchException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.kyonggi.Capstone_Develop.domain.schedule.Status.END;
import static com.kyonggi.Capstone_Develop.domain.schedule.Status.PROCEEDING;
import static com.kyonggi.Capstone_Develop.domain.schedule.Step.RECEIVED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ScheduleTest {
    @Test
    @DisplayName("시작 날짜가 종료 날짜보다 이후이면 예외가 발생한다.")
    void throwException_invalidDate() {
        // given
        LocalDate wrongStartDate = LocalDate.of(2023, 3, 16);
        LocalDate wrongEndDate = LocalDate.of(2023, 3, 15);
    
        assertThatThrownBy(() -> new Schedule(RECEIVED, PROCEEDING, wrongStartDate, wrongEndDate))
                .isInstanceOf(DateMismatchException.class)
                .hasMessage("시작 날짜가 종료 날짜보다 느립니다.");
    }
    
    @Test
    @DisplayName("진행일정을 생성한다.")
    void construct() {
        assertDoesNotThrow(() -> new Schedule(
                RECEIVED,
                PROCEEDING,
                LocalDate.of(2023, 3, 7),
                LocalDate.of(2023, 3, 15)
        ));
    }
    
    @Test
    @DisplayName("진행일정을 수정하고 시간에 맞춰 상태가 변경된다.")
    void changeSchedule() {
        // given
        Schedule schedule = new Schedule(
                RECEIVED,
                PROCEEDING,
                LocalDate.of(2023, 3, 7),
                LocalDate.of(2023, 3, 15)
        );
        LocalDate changeStartDate = LocalDate.of(2023, 3, 8);
        LocalDate changeEndDate = LocalDate.of(2023, 3, 16);
        Status expectedStatus = END;
    
        // when
        schedule.changeStartDate(changeStartDate);
        schedule.changeEndDate(changeEndDate);
        schedule.changeStatus();
      
        // then
        assertAll(
                () -> assertThat(schedule.getStatus()).isEqualTo(expectedStatus),
                () -> assertThat(schedule.getStartDate()).isEqualTo(changeStartDate),
                () -> assertThat(schedule.getEndDate()).isEqualTo(changeEndDate)
        );
    }
}