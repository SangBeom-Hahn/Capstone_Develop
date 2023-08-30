package com.kyonggi.Capstone_Develop.controller.dto.scheduleboard;

import com.kyonggi.Capstone_Develop.service.dto.schedule.ScheduleBoardResponseDto;
import com.kyonggi.Capstone_Develop.service.dto.schedule.ScheduleResponseDto;
import com.kyonggi.Capstone_Develop.service.dto.schedule.SchedulesResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AllScheduleResponse {
    private Long id;
    
    private String receive;
    
    private String proposal;
    
    private String middleReport;
    
    private String finalReport;
    
    private String finalPass;
    
    private String otherQualification;
    
    List<ScheduleResponseDto> schedules;
    
    public static AllScheduleResponse of(
            SchedulesResponseDto schedulesResponseDto,
            ScheduleBoardResponseDto scheduleBoardResponseDto
    ) {
        return new AllScheduleResponse(
                scheduleBoardResponseDto.getId(),
                scheduleBoardResponseDto.getReceive(),
                scheduleBoardResponseDto.getProposal(),
                scheduleBoardResponseDto.getMiddleReport(),
                scheduleBoardResponseDto.getFinalReport(),
                scheduleBoardResponseDto.getFinalPass(),
                scheduleBoardResponseDto.getOtherQualification(),
                schedulesResponseDto.getScheduleResponseDtos()
        );
    }
}
