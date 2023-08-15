package com.kyonggi.Capstone_Develop.service;

import com.kyonggi.Capstone_Develop.controller.dto.auth.LoginRequest;
import com.kyonggi.Capstone_Develop.domain.student.*;
import com.kyonggi.Capstone_Develop.exception.IdPasswordMismatchException;
import com.kyonggi.Capstone_Develop.exception.NoSuchMemberException;
import com.kyonggi.Capstone_Develop.repository.StudentRepository;
import com.kyonggi.Capstone_Develop.service.dto.auth.TokenResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
@SpringBootTest
@Transactional
class AuthServiceTest {
    @Autowired
    private AuthService authService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private StudentRepository studentRepository;
    
    private Student dummyStudent;
    
    @BeforeEach
    void setUp() {
        dummyStudent = new Student(
                "cherry1",
                passwordEncoder.encode("123#a1"),
                LocalDate.of(2023, 07, 18),
                "컴퓨터공학부",
                Grade.FOURTH,
                PhoneNumber.from("010-1111-1111"),
                Sex.FEMAIL,
                "한상범",
                Email.from("1@naver.com"),
                "20182222"
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
        )).isInstanceOf(NoSuchMemberException.class)
                .hasMessage("없는 ID 입니다. 다시 로그인 해주세요. loginId={no}");
    }
    
    @Test
    @DisplayName("id와 비밀번호가 일치하지 않으면 예외가 발생한다.")
    void throwException_mismatchIdPassword() {
        // given
        LoginRequest loginRequest = new LoginRequest("cherry1", "no");
      
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
        LoginRequest loginRequest = new LoginRequest("cherry1", "123#a1");
        
        // when
        TokenResponseDto tokenResponseDto = authService.login(loginRequest.getLoginId(), loginRequest.getLoginPassword());
        log.info("토크 = {}", tokenResponseDto);
        
        // then
        assertThat(tokenResponseDto).isNotNull();
    }
}