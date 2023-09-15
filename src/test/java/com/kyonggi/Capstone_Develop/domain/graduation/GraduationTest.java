package com.kyonggi.Capstone_Develop.domain.graduation;

import com.kyonggi.Capstone_Develop.domain.student.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class GraduationTest {
    @Test
    @DisplayName("현재보다 졸업 일자가 과거이면 예외가 발생한다.")
    void throwException_InvalidGraduationDate() {
        assertThatThrownBy(() -> new Graduation(
                Method.THESIS,
                Status.UNAPPROVAL,
                Step.RECEIVED,
                true,
                LocalDate.MIN,
                "김교수님"
        ));
    }
    
    // TODO: 학생 필드 바뀐 거 공지사항 merge하고 다시 테스트하기
    @Test
    @DisplayName("졸업자를 생성하고 매핑 테이블에 저장한다.")
    void addApply() {
        // given
        Student dummyStudent = new Student(
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
    
        Graduation dummyGraduation = new Graduation(
                Method.THESIS,
                Status.UNAPPROVAL,
                Step.RECEIVED,
                true,
                LocalDate.MAX,
                "김교수님"
        );
        
        // when
        Apply apply = new Apply(dummyStudent, dummyGraduation);

        // then
        assertAll(
                () -> assertThat(dummyStudent.getApplies()).hasSize(1),
                () -> assertThat(apply.getStudent()).isEqualTo(dummyStudent),
                () -> assertThat(dummyGraduation.getApplies()).hasSize(1),
                () -> assertThat(apply.getGraduation()).isEqualTo(dummyGraduation)
        );
    }
    
    @Test
    @DisplayName("졸업자 조회를 생성한다.")
    void construct() {
        assertDoesNotThrow(() -> new Graduation(
                Method.THESIS,
                Status.UNAPPROVAL,
                Step.RECEIVED,
                true,
                LocalDate.MAX,
                "김교수님"
        ));
    }
}