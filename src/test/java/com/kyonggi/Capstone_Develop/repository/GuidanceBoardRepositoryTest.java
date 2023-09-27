package com.kyonggi.Capstone_Develop.repository;

import com.kyonggi.Capstone_Develop.domain.GuidanceBoard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class GuidanceBoardRepositoryTest extends RepositoryTest{
    
    @Test
    @DisplayName("안내 및 내규 내용을 수정한다.")
    void updateContent() {
        // given
        String expectedContent = "expectedContent";
        
        // when
        GuidanceBoard findGuidanceBoard = guidanceBoardRepository.findById(1L)
                .orElseThrow();
        findGuidanceBoard.changeContent(expectedContent);
        
        // then
        assertThat(findGuidanceBoard.getContent())
                .isEqualTo(expectedContent);
    }
}