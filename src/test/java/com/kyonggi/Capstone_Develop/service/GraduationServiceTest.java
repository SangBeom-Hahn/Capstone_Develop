package com.kyonggi.Capstone_Develop.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@SpringBootTest
class GraduationServiceTest {
    @Autowired
    private GraduationService graduationService;

    @Test
    @DisplayName("졸업")
    void GraduationServiceTest() {
        // given

        // when

        // then
    }
}