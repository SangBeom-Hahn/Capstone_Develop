package com.kyonggi.Capstone_Develop.service.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class ScheduleBoardUpdateRequestDto {
    private String receive;
    
    private String proposal;
    
    private String middleReport;
    
    private String finalReport;
    
    private String finalPass;
    
    private String otherQualification;
}
