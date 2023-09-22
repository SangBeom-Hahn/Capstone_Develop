package com.kyonggi.Capstone_Develop.service;

import com.kyonggi.Capstone_Develop.controller.dto.auth.LoginRequest;
import com.kyonggi.Capstone_Develop.domain.refreshtoken.RefreshToken;
import com.kyonggi.Capstone_Develop.domain.student.*;
import com.kyonggi.Capstone_Develop.exception.IdPasswordMismatchException;
import com.kyonggi.Capstone_Develop.exception.NoSuchMemberIdException;
import com.kyonggi.Capstone_Develop.service.dto.auth.TokenResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AuthServiceTest extends ServiceTest{
    private Student dummyStudent;
    
    @BeforeEach
    void setUp() {
        dummyStudent = new Student(
                "201812709",
                passwordEncoder.encode("123#a1"),
                LocalDate.of(2023, 07, 18),
                "컴퓨터공학부",
                PhoneNumber.from("010-1111-1111"),
                Sex.MALE,
                "한상범",
                Email.from("1@naver.com"),
                RoleType.STUDENT,
                "answerPW",
                Classification.from("UNDERGRADUATE_STUDENT")
        );
        studentRepository.save(dummyStudent);
    }
    
    @Test
    @DisplayName("유효하지 않은 id로 로그인하면 예외가 발생한다.")
    void throwException_invalidLoginId() {
        // given
        LoginRequest loginRequest = new LoginRequest("no", "no");
        
        // then
        assertThatThrownBy(() -> authService.login(
                loginRequest.getLoginId(), loginRequest.getLoginPassword()
        )).isInstanceOf(NoSuchMemberIdException.class)
                .hasMessage("없는 ID 입니다. 다시 로그인 해주세요. loginId={no}");
    }
    
    @Test
    @DisplayName("id와 비밀번호가 일치하지 않으면 예외가 발생한다.")
    void throwException_mismatchIdPassword() {
        // given
        LoginRequest loginRequest = new LoginRequest("201812709", "no");
      
        // then
        assertThatThrownBy(() -> authService.login(
                loginRequest.getLoginId(),
                loginRequest.getLoginPassword()
        )).isInstanceOf(IdPasswordMismatchException.class)
                .hasMessage("아이디 혹은 비밀번호를 확인해주세요.");
    }
    
    @Test
    @DisplayName("로그인 요청을 받아서 토큰을 생성한다.")
    void createToken() {
        // given
        LoginRequest loginRequest = new LoginRequest("201812709", "123#a1");
        
        // when
        TokenResponseDto tokenResponseDto =
                authService.login(loginRequest.getLoginId(), loginRequest.getLoginPassword());
        
        // then
        assertThat(tokenResponseDto).isNotNull();
    }
    
    @Test
    @DisplayName("리프레시 토큰을 무효화한다.")
    void logout() {
        // given
        final RefreshToken refreshToken = RefreshToken.createBy(dummyStudent.getId(), () -> "refreshToken");
        
        // when
        refreshTokenRepository.save(refreshToken);
        refreshTokenRepository.deleteByTokenValue(refreshToken.getTokenValue());
        final Optional<RefreshToken> findRefreshToken = refreshTokenRepository.findByTokenValue(refreshToken.getTokenValue());
        
        // then
        assertThat(findRefreshToken).isEmpty();
    }
}