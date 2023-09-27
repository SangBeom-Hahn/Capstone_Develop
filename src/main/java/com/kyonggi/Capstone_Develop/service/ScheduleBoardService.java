package com.kyonggi.Capstone_Develop.service;

import com.kyonggi.Capstone_Develop.domain.schedule.ScheduleBoard;
import com.kyonggi.Capstone_Develop.exception.NotFoundScheduleBoardException;
import com.kyonggi.Capstone_Develop.repository.ScheduleBoardRepository;
import com.kyonggi.Capstone_Develop.service.dto.schedule.ScheduleBoardResponseDto;
import com.kyonggi.Capstone_Develop.service.dto.schedule.ScheduleBoardUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kyonggi.Capstone_Develop.utils.ConstantMessage.SCHEDULE_NUMBER;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleBoardService {
    private final ScheduleBoardRepository scheduleBoardRepository;
    
    public ScheduleBoardResponseDto findScheduleBoard() {
        ScheduleBoard scheduleBoard = scheduleBoardRepository.findById(SCHEDULE_NUMBER)
                .orElseThrow(() -> new NotFoundScheduleBoardException(SCHEDULE_NUMBER));
    
        return ScheduleBoardResponseDto.from(scheduleBoard);
    }
    
    public void updateScheduleBoard(ScheduleBoardUpdateRequestDto scheduleBoardUpdateRequestDto) {
        ScheduleBoard scheduleBoard = scheduleBoardRepository.findById(SCHEDULE_NUMBER)
                .orElseThrow(() -> new NotFoundScheduleBoardException(SCHEDULE_NUMBER));
    
        changeScheduleBoard(scheduleBoardUpdateRequestDto, scheduleBoard);
    }
    
    private void changeScheduleBoard(ScheduleBoardUpdateRequestDto scheduleBoardUpdateRequestDto, ScheduleBoard scheduleBoard) {
        scheduleBoard.changeReceive(scheduleBoardUpdateRequestDto.getReceive());
        scheduleBoard.changeProposal(scheduleBoardUpdateRequestDto.getProposal());
        scheduleBoard.changeMiddleReport(scheduleBoardUpdateRequestDto.getMiddleReport());
        scheduleBoard.changeFinalReport(scheduleBoardUpdateRequestDto.getFinalReport());
        scheduleBoard.changeFinalPass(scheduleBoardUpdateRequestDto.getFinalPass());
        scheduleBoard.changeOtherQualification(scheduleBoardUpdateRequestDto.getOtherQualification());
    }
}
