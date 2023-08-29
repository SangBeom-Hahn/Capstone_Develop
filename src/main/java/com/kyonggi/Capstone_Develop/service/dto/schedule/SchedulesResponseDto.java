package com.kyonggi.Capstone_Develop.service.dto.schedule;

import lombok.Getter;

import java.util.List;

@Getter
public class SchedulesResponseDto {
    List<ScheduleResponseDto> scheduleResponseDtos;
    
    public SchedulesResponseDto(List<ScheduleResponseDto> scheduleResponseDtos) {
        this.scheduleResponseDtos = scheduleResponseDtos;
    }
    
    public static SchedulesResponseDto from(List<ScheduleResponseDto> scheduleResponseDtos) {
        return new SchedulesResponseDto(scheduleResponseDtos);
    }
}
