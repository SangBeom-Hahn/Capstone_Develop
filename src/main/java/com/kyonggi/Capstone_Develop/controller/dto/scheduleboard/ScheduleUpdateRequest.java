package com.kyonggi.Capstone_Develop.controller.dto.scheduleboard;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleUpdateRequest {
    @NotNull
    private LocalDate startDate;
    
    @NotNull
    private LocalDate endDate;
}
