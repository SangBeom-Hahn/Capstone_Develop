package com.kyonggi.Capstone_Develop.repository.situation;

import com.kyonggi.Capstone_Develop.domain.graduation.*;
import com.kyonggi.Capstone_Develop.domain.situation.Submit;
import com.kyonggi.Capstone_Develop.domain.student.*;
import com.kyonggi.Capstone_Develop.repository.RepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.kyonggi.Capstone_Develop.domain.situation.Approval.UNAPPROVAL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SubmitRepositoryTest extends RepositoryTest {
    private Student student;
    
    private Graduation graduation;
    
    private Apply apply;
    
    @BeforeEach
    void setUp() {
        student = new Student(
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
        
        graduation = new Graduation(
                Method.THESIS,
                Status.UNAPPROVAL,
                Step.RECEIVED,
                true,
                LocalDate.MAX,
                "김교수님"
        );
        studentRepository.save(student);
        graduationRepository.save(graduation);
        apply = new Apply(student, graduation);
        applyRepository.save(apply);
    }
    
    @Test
    @DisplayName("")
    void save() {
        // given
        Submit submit = new Submit(
                apply,
                "김교수님",
                LocalDate.MAX,
                true,
                UNAPPROVAL,
                null
        );
    
        // when
        Submit saveSubmit = submitRepository.save(submit);
    
        // then
        assertAll(
                () -> assertThat(saveSubmit.getId()).isNotNull(),
                () -> assertThat(saveSubmit).isEqualTo(submit)
        );
    }
}