package com.kyonggi.Capstone_Develop.controller.dto.noticeboard;

import com.kyonggi.Capstone_Develop.controller.dto.RequestTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.kyonggi.Capstone_Develop.controller.dto.ValidateMessage.EMPTY_MESSAGE;
import static com.kyonggi.Capstone_Develop.controller.dto.ValidateMessage.NOTICE_BOARD_SIZE_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class NoticeBoardSaveRequestTest extends RequestTest {
    @ParameterizedTest
    @ValueSource(strings = {"", "a"})
    @DisplayName("1자 미만 255자 초과 내용이 들어오면 처리한다.")
    void invalidContentSize(String content) {
        NoticeBoardSaveRequest noticeBoardSaveRequest = new NoticeBoardSaveRequest(
                content.repeat(256),
                false,
                "title",
                1
        );
    
        assertThat(getConstraintViolations(noticeBoardSaveRequest).stream()
                .anyMatch(violation -> violation.getMessage().equals(NOTICE_BOARD_SIZE_MESSAGE)))
                .isTrue();
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"", "a"})
    @DisplayName("1자 미만 255자 초과 제목이 들어오면 처리한다.")
    void invalidTitleSize(String title) {
        NoticeBoardSaveRequest noticeBoardSaveRequest = new NoticeBoardSaveRequest(
                "content",
                false,
                title.repeat(256),
                1
        );
        
        assertThat(getConstraintViolations(noticeBoardSaveRequest).stream()
                .anyMatch(violation -> violation.getMessage().equals(NOTICE_BOARD_SIZE_MESSAGE)))
                .isTrue();
    }
    
    @ParameterizedTest
    @NullSource
    @DisplayName("조회수에 빈 정수가 들어오면 처리한다.")
    void blankViews(Integer views) {
        NoticeBoardSaveRequest noticeBoardSaveRequest = new NoticeBoardSaveRequest(
                "content",
                false,
                "title",
                views
        );
    
        assertThat(getConstraintViolations(noticeBoardSaveRequest).stream()
                .anyMatch(violation -> violation.getMessage().equals(EMPTY_MESSAGE)))
                .isTrue();
    }
}