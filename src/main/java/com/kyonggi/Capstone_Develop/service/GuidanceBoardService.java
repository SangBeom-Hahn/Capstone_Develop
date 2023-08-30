package com.kyonggi.Capstone_Develop.service;

import com.kyonggi.Capstone_Develop.domain.GuidanceBoard;
import com.kyonggi.Capstone_Develop.exception.NotFoundGuidanceBoardException;
import com.kyonggi.Capstone_Develop.repository.GuidanceBoardRepository;
import com.kyonggi.Capstone_Develop.service.dto.GuidanceBoardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GuidanceBoardService {
    private final GuidanceBoardRepository guidanceBoardRepository;
    
    public GuidanceBoardResponseDto findGuidanceBoard() {
        GuidanceBoard guidanceBoard = guidanceBoardRepository.findById(1L)
                .orElseThrow(() -> new NotFoundGuidanceBoardException(1L));
    
        return GuidanceBoardResponseDto.from(guidanceBoard);
    }
    
    public void updateGuidanceBoard(String content) {
        GuidanceBoard guidanceBoard = guidanceBoardRepository.findById(1L)
                .orElseThrow(() -> new NotFoundGuidanceBoardException(1L));
        
        guidanceBoard.changeContent(content);
    }
}
