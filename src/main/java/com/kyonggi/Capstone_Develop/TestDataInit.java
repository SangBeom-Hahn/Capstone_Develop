package com.kyonggi.Capstone_Develop;

import com.kyonggi.Capstone_Develop.domain.student.*;
import com.kyonggi.Capstone_Develop.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.time.LocalDate;

@RequiredArgsConstructor
public class TestDataInit {
    private final StudentRepository studentRepository;
    
    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        Student student = new Student(
                "jack",
                "123#a",
                LocalDate.of(2023, 07, 18),
                "컴퓨터공학부",
                Grade.FOURTH,
                PhoneNumber.from("010-1111-1111"),
                Sex.FEMAIL,
                "한상범",
                Email.from("1@naver.com"),
                "20181111"
        );
        studentRepository.save(student);
    }
}
