package com.kyonggi.Capstone_Develop;

import com.kyonggi.Capstone_Develop.domain.student.*;
import com.kyonggi.Capstone_Develop.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@RequiredArgsConstructor
public class TestDataInit {
    private final StudentRepository studentRepository;
    
    protected final PasswordEncoder passwordEncoder;

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        Student student = new Student(
                "cherry2",
                passwordEncoder.encode("123#a2"),
                LocalDate.of(2023, 07, 18),
                "컴퓨터공학부",
                Grade.FOURTH,
                PhoneNumber.from("010-1111-1111"),
                Sex.FEMAIL,
                "한상범",
                Email.from("1@naver.com"),
                "20182222"
        );
        studentRepository.save(student);
    }
}
