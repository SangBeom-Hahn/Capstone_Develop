package com.kyonggi.Capstone_Develop;

import com.kyonggi.Capstone_Develop.repository.StudentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DummyConfig {
    @Bean
    @Profile("local")
    public TestDataInit testDataInit(StudentRepository studentRepository, PasswordEncoder passwordEncoder) {
        return new TestDataInit(studentRepository, passwordEncoder);
    }
}
