package com.kyonggi.Capstone_Develop.domain.student;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class StudentTest {
    @ParameterizedTest
    @MethodSource("invalidLengthLoginId")
    @DisplayName("회원가입할 때 2 ~ 16 길이의 범위를 넘어가는 id는 예외가 발생한다.")
    void throwException_invalidLength(String invalidLengthLoginId) {
        assertThatThrownBy(() -> new Student(
                invalidLengthLoginId,
                "****",
                LocalDate.of(2023, 07, 18),
                "컴퓨터공학부",
                Grade.FOURTH,
                "010-1111-1111",
                Sex.FEMAIL,
                "한상범",
                "1@naver.com",
                "20181111"
        )).isInstanceOf(IllegalArgumentException.class);
    }
    
    private static Stream<Arguments> invalidLengthLoginId() {
        return Stream.of(
                Arguments.of("0123456789012345"),
                Arguments.of("0")
        );
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"12", "1a", "aa", "#a", "#1", "##"})
    @DisplayName("특수문자, 알파벳, 숫자를 전부 가지지 않는 아이디를 입력하면 예외가 발생한다.")
    void throwException_hasNotSpecialCharacter(String invalidIncludeConditionLoginId) {
        assertThatThrownBy(() -> new Student(
                invalidIncludeConditionLoginId,
                "****",
                LocalDate.of(2023, 07, 18),
                "컴퓨터공학부",
                Grade.FOURTH,
                "010-1111-1111",
                Sex.FEMAIL,
                "한상범",
                "1@naver.com",
                "20181111"
        )).isInstanceOf(IllegalArgumentException.class);
    }
    
    @Test
    @DisplayName("올바르지 않은 형식의 이메일을 가진 아이디를 입력하면 예외가 발생한다.")
    void throwException_invalidEmail() {
        String invalidEmail = "abc";
        
        assertThatThrownBy(() -> new Student(
                "123#a",
                "****",
                LocalDate.of(2023, 07, 18),
                "컴퓨터공학부",
                Grade.FOURTH,
                "010-1111-1111",
                Sex.FEMAIL,
                "한상범",
                invalidEmail,
                "20181111"
        )).isInstanceOf(IllegalArgumentException.class);
    }
    
    @Test
    @DisplayName("올바르지 않은 형식의 전화번호를 가진 아이디를 입력하면 예외가 발생한다.")
    void throwException_invalidPhoneNumber() {
        String invalidPhoneNumber = "01011111111";
        
        assertThatThrownBy(() -> new Student(
                "123#a",
                "****",
                LocalDate.of(2023, 07, 18),
                "컴퓨터공학부",
                Grade.FOURTH,
                invalidPhoneNumber,
                Sex.FEMAIL,
                "한상범",
                "1@naver.com",
                "20181111"
        )).isInstanceOf(IllegalArgumentException.class);
    }
    
    @Test
    @DisplayName("학생을 생성한다.")
    void construct() {
        assertDoesNotThrow(() -> new Student(
                "123#a",
                "****",
                LocalDate.of(2023, 07, 18),
                "컴퓨터공학부",
                Grade.FOURTH,
                "010-1111-1111",
                Sex.FEMAIL,
                "한상범",
                "1@naver.com",
                "20181111"
        ));
    }
}
