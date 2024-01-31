package com.kyonggi.Capstone_Develop.service.dto.schedule;

import com.kyonggi.Capstone_Develop.domain.schedule.ScheduleBoard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
@Getter
@ToString
@AllArgsConstructor
public class ScheduleBoardResponseDto {
    private Long id;
    
    private String receive;
    
    private String proposal;
    
    private String middleReport;
    
    private String finalReport;
    
    private String finalPass;
    
    private String otherQualification;
    
    public static ScheduleBoardResponseDto from(ScheduleBoard scheduleBoard) {
        return new ScheduleBoardResponseDto(
                scheduleBoard.getId(),
                scheduleBoard.getReceive(),
                scheduleBoard.getProposal(),
                scheduleBoard.getMiddleReport(),
                scheduleBoard.getFinalReport(),
                scheduleBoard.getFinalPass(),
                scheduleBoard.getOtherQualification()
        );
    }
}
