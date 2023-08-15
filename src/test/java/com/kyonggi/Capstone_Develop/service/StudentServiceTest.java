package com.kyonggi.Capstone_Develop.service;

import com.kyonggi.Capstone_Develop.domain.student.Email;
import com.kyonggi.Capstone_Develop.domain.student.Grade;
import com.kyonggi.Capstone_Develop.domain.student.PhoneNumber;
import com.kyonggi.Capstone_Develop.domain.student.Sex;
import com.kyonggi.Capstone_Develop.exception.DuplicateLoginIdException;
import com.kyonggi.Capstone_Develop.service.dto.student.StudentRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
@SpringBootTest
class StudentServiceTest {
    @Autowired
    private StudentService studentService;
    
    /**
     * 이건 jpa 레포 이슈로 아직 중복 회원가입 처리 불가
     */
    @Test
    @DisplayName("중복된 아이디로 회원가입하면 예외가 발행한다.")
    void throwException_duplicateLoginId() {
        // given
        StudentRequestDto studentRequestDto1 = new StudentRequestDto(
                "dummy",
                "dummy123#a",
                LocalDate.of(2023, 07, 18),
                "dummy",
                Grade.FOURTH,
                PhoneNumber.from("010-1111-1111"),
                Sex.FEMAIL,
                "dummy",
                Email.from("dummy1@naver.com"),
                "dummy"
        );
    
        StudentRequestDto studentRequestDto2 = new StudentRequestDto(
                "dummy",
                "dummy123#a",
                LocalDate.of(2023, 07, 18),
                "dummy",
                Grade.FOURTH,
                PhoneNumber.from("010-1111-1111"),
                Sex.FEMAIL,
                "dummy",
                Email.from("dummy1@naver.com"),
                "dummy"
        );
    
        // when
        studentService.save(studentRequestDto1);
        
        // then
        assertThatThrownBy(() -> studentService.save(studentRequestDto2))
                .isInstanceOf(DuplicateLoginIdException.class);
    }
    
    @Test
    @DisplayName("올바른 형식으로 회원이 회원가입을 한다.")
    void save() {
        // given
        StudentRequestDto studentRequestDto = new StudentRequestDto(
                "cherry",
                "123#a",
                LocalDate.of(2023, 07, 18),
                "컴퓨터공학부",
                Grade.FOURTH,
                PhoneNumber.from("010-1111-1111"),
                Sex.FEMAIL,
                "한상범",
                Email.from("1@naver.com"),
                "20182222"
        );
    
        // when
        assertDoesNotThrow(() -> studentService.save(studentRequestDto));
    }
}