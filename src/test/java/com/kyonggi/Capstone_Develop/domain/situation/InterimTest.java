package com.kyonggi.Capstone_Develop.domain.situation;

import com.kyonggi.Capstone_Develop.domain.graduation.*;
import com.kyonggi.Capstone_Develop.domain.student.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.kyonggi.Capstone_Develop.domain.situation.Approval.UNAPPROVAL;
import static org.junit.jupiter.api.Assertions.*;

class InterimTest {
    @Test
    @DisplayName("중간보고서 폼을 생성한다.")
    void construct() {
        // given
        Student dummyStudent = new Student(
                "201812709",
                "dummyPassword",
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
        
        Graduation dummyGraduation = new Graduation(
                Method.THESIS,
                Status.UNAPPROVAL,
                Step.RECEIVED,
                true,
                LocalDate.MAX,
                "김교수님"
        );
        
        Apply apply = new Apply(dummyStudent, dummyGraduation);
        
        // then
        assertDoesNotThrow(() -> new Interim(
                apply,
                "title",
                "division",
                "text",
                "plan",
                UNAPPROVAL,
                "rejectReason"
        ));
    }
}