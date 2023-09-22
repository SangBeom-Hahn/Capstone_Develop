package com.kyonggi.Capstone_Develop.service;

import com.kyonggi.Capstone_Develop.domain.student.Classification;
import com.kyonggi.Capstone_Develop.domain.student.Email;
import com.kyonggi.Capstone_Develop.domain.student.PhoneNumber;
import com.kyonggi.Capstone_Develop.domain.student.Sex;
import com.kyonggi.Capstone_Develop.exception.DuplicateLoginIdException;
import com.kyonggi.Capstone_Develop.service.dto.student.StudentRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class StudentServiceTest extends ServiceTest{
    @Test
    @DisplayName("중복된 아이디로 회원가입하면 예외가 발행한다.")
    void throwException_duplicateLoginId() {
        // given
        StudentRequestDto studentRequestDto1 = createStudentRequestDto();
        StudentRequestDto studentRequestDto2 = createStudentRequestDto();
    
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
                "201812709",
                "123#a",
                "한상범",
                Sex.FEMALE,
                LocalDate.of(2023, 07, 18),
                Email.from("1@naver.com"),
                PhoneNumber.from("010-1111-1111"),
                Classification.from("UNDERGRADUATE_STUDENT"),
                "컴퓨터공학부",
                "서울"
        );
    
        // when
        assertDoesNotThrow(() -> studentService.save(studentRequestDto));
    }
    
    private static StudentRequestDto createStudentRequestDto() {
        return new StudentRequestDto(
                "dummy",
                "dummy123#a",
                "dummy",
                Sex.FEMALE,
                LocalDate.of(2023, 07, 18),
                Email.from("dummy1@naver.com"),
                PhoneNumber.from("010-1111-1111"),
                Classification.from("UNDERGRADUATE_STUDENT"),
                "dummy",
                "dummy"
        );
    }
}