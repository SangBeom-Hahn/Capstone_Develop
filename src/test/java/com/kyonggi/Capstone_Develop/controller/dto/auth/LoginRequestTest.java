package com.kyonggi.Capstone_Develop.controller.dto.auth;

import com.kyonggi.Capstone_Develop.controller.dto.RequestTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static com.kyonggi.Capstone_Develop.controller.dto.ValidateMessage.EMPTY_MESSAGE;
import static com.kyonggi.Capstone_Develop.controller.dto.ValidateMessage.PASSWORD_FORMAT_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;

class LoginRequestTest extends RequestTest {
    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("로그인 아이디에 빈 문자열이 들어오면 처리한다.")
    void blankLoginId(String loginId) {
        LoginRequest loginRequest = new LoginRequest(loginId, "password");
    
        assertThat(isEmpty(loginRequest)).isTrue();
    }
    
    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("로그인 비밀번호에 빈 문자열이 들어오면 처리한다.")
    void blankLoginPassword(String loginPassword) {
        LoginRequest loginRequest = new LoginRequest("loginId", loginPassword);
    
        assertThat(isEmpty(loginRequest)).isTrue();
    }
    
    @ParameterizedTest
    @CsvSource(value = {"test!:true", "test1234:true", "1234test:true", "1234!:true", "한글도 실패:true", "123#a2:false"}, delimiter = ':')
    @DisplayName("로그인 비밀번호에 옳지 않는 형식의 문자열이 들어오면 처리한다.")
    void invalidPassword(String password, boolean flag) {
        LoginRequest loginRequest = new LoginRequest("loginId", password);
    
        assertThat(getConstraintViolations(loginRequest).stream()
                .anyMatch(violation -> violation.getMessage().equals(PASSWORD_FORMAT_MESSAGE)))
                .isEqualTo(flag);
    }
    
    private boolean isEmpty(LoginRequest loginRequest) {
        return getConstraintViolations(loginRequest).stream()
                .anyMatch(violation -> violation.getMessage().equals(EMPTY_MESSAGE));
    }
}