package com.kyonggi.Capstone_Develop.repository;

import com.kyonggi.Capstone_Develop.config.JpaAuditingConfig;
import com.kyonggi.Capstone_Develop.domain.GuidanceBoard;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Import(JpaAuditingConfig.class)
@Transactional
@DataJpaTest
@RequiredArgsConstructor
class GuidanceBoardRepositoryTest {
    @Autowired
    private GuidanceBoardRepository guidanceBoardRepository;
    
    private GuidanceBoard guidanceBoard;
    
    @BeforeEach
    void setUp() {
        guidanceBoard = new GuidanceBoard("content");
    }
    
    @Test
    @DisplayName("안내 및 내규 내용을 수정한다.")
    void updateContent() {
        // given
        Long guidanceBoardId = guidanceBoardRepository.save(guidanceBoard)
                .getId();
        String expectedContent = "expectedContent";
    
        // when
        guidanceBoard.changeContent(expectedContent);
        GuidanceBoard findGuidanceBoard = guidanceBoardRepository.findById(guidanceBoardId)
                .orElseThrow();
    
        // then
        assertThat(findGuidanceBoard.getContent())
                .isEqualTo(expectedContent);
    }
}