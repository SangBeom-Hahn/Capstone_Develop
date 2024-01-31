package com.kyonggi.Capstone_Develop.controller.dto.scheduleboard;

import com.kyonggi.Capstone_Develop.service.dto.schedule.ScheduleBoardUpdateRequestDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleBoardUpdateRequest {
    @NotBlank
    private String receive;
    
    @NotBlank
    private String proposal;
    
    @NotBlank
    private String middleReport;
    
    @NotBlank
    private String finalReport;
    
    @NotBlank
    private String finalPass;
    
    @NotBlank
    private String otherQualification;
    
    public ScheduleBoardUpdateRequestDto toServiceDto() {
        return new ScheduleBoardUpdateRequestDto(
                receive,
                proposal,
                middleReport,
                finalReport,
                finalPass,
                otherQualification
        );
    }
}
