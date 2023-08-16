package com.kyonggi.Capstone_Develop.domain.student;

import com.kyonggi.Capstone_Develop.exception.InvalidEmailFormatException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class EmailTest {
    @Test
    @DisplayName("잘못된 이메일 형식을 입력하면 예외가 발생한다.")
    void throwException_invalidEmailFormat() {
        assertThatThrownBy(() -> Email.from("abc"))
                .isInstanceOf(InvalidEmailFormatException.class);
    }
    
    @Test
    @DisplayName("이메일 객체를 생성한다.")
    void construct() {
        assertDoesNotThrow(() -> Email.from("1@naver.com"));
    }
}