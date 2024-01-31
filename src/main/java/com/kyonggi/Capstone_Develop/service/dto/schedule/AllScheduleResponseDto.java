package com.kyonggi.Capstone_Develop.service.dto.schedule;

import com.kyonggi.Capstone_Develop.domain.schedule.ScheduleBoard;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AllScheduleResponseDto {
    private Long id;
    
    private String receive;
    
    private String proposal;
    
    private String middleReport;
    
    private String finalReport;
    
    private String finalPass;
    
    private String otherQualification;
    
    List<ScheduleResponseDto> schedules;
    
    public static AllScheduleResponseDto of(
            ScheduleBoard scheduleBoard,
            List<ScheduleResponseDto> schedules
    ) {
        return new AllScheduleResponseDto(
                scheduleBoard.getId(),
                scheduleBoard.getReceive(),
                scheduleBoard.getProposal(),
                scheduleBoard.getMiddleReport(),
                scheduleBoard.getFinalReport(),
                scheduleBoard.getFinalPass(),
                scheduleBoard.getOtherQualification(),
                schedules
        );
    }
}
