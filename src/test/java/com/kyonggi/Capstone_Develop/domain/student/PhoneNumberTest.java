package com.kyonggi.Capstone_Develop.domain.student;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PhoneNumberTest {
    @Test
    @DisplayName("잘못된 형식의 전화번호를 입력하면 예외가 발생한다.")
    void throwException_invalidPhoneNumberFormat() {
        assertThatThrownBy(() -> PhoneNumber.from("01011111111"))
                .isInstanceOf(IllegalArgumentException.class);
    }
    
    @Test
    @DisplayName("전화번호 객체를 생성한다.")
    void construct() {
        assertDoesNotThrow(() -> PhoneNumber.from("010-1111-1111"));
    }
}