package com.kyonggi.Capstone_Develop.controller.dto.situation;

import com.kyonggi.Capstone_Develop.controller.dto.RequestTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static com.kyonggi.Capstone_Develop.controller.dto.ValidateMessage.DATE_FORMAT;
import static org.assertj.core.api.Assertions.assertThat;

class SubmitSaveRequestTest extends RequestTest {
    @ParameterizedTest
    @CsvSource(value = {"2023-02-09:false"}, delimiter = ':')
    @DisplayName("졸업날짜 입력시 옳지 않은 형식의 날짜가 들어오면 처리한다.")
    void invalidDate(LocalDate date, boolean flag) {
        SubmitSaveRequest submitSaveRequest = new SubmitSaveRequest("name", date, false);
    
        assertThat(getConstraintViolations(submitSaveRequest).stream()
                .anyMatch(violation -> violation.getMessage().equals(DATE_FORMAT)))
                .isEqualTo(flag);
    }
}