package com.kyonggi.Capstone_Develop.repository;

import com.kyonggi.Capstone_Develop.domain.student.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class StudentRepositoryTest extends RepositoryTest{
    private Student sangbeom;
    
    @BeforeEach
    void setUp() {
        sangbeom = new Student(
                "cherry",
                "123#a",
                LocalDate.of(2023, 07, 18),
                "컴퓨터공학부",
                Grade.FOURTH,
                PhoneNumber.from("010-1111-1111"),
                Sex.FEMALE,
                "한상범",
                Email.from("1@naver.com"),
                "20182222"
                ,RoleType.STUDENT
        );
    }
    
    @Test
    @DisplayName("올바른 형식으로 학생이 회원가입을 한다.")
    void save() {
        // when
        Student saveStudent = studentRepository.save(sangbeom);
        
        // then
        assertAll(
                () -> assertThat(saveStudent.getId()).isNotNull(),
                () -> assertThat(saveStudent).isEqualTo(sangbeom)
        );
    }
}