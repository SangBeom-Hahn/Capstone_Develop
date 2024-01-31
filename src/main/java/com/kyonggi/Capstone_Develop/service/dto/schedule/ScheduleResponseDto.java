package com.kyonggi.Capstone_Develop.service.dto.schedule;

import com.kyonggi.Capstone_Develop.domain.schedule.Schedule;
import com.kyonggi.Capstone_Develop.domain.schedule.Status;
import com.kyonggi.Capstone_Develop.domain.schedule.Step;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
@AllArgsConstructor
public class ScheduleResponseDto {
    private Long id;
    
    private Step step;
    
    private Status status;
    
    private LocalDate startDate;
    
    private LocalDate endDate;
    
    public static ScheduleResponseDto from(Schedule schedule) {
        return new ScheduleResponseDto(
                schedule.getId(),
                schedule.getStep(),
                schedule.getStatus(),
                schedule.getStartDate(),
                schedule.getEndDate()
        );
    }
}
