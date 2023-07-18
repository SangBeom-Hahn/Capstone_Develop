package com.kyonggi.Capstone_Develop;

import com.kyonggi.Capstone_Develop.repository.StudentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class DummyConfig {
    @Bean
    @Profile("local")
    public TestDataInit testDataInit(StudentRepository studentRepository) {
        return new TestDataInit(studentRepository);
    }
}
