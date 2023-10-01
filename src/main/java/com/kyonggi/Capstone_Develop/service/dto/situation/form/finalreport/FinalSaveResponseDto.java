package com.kyonggi.Capstone_Develop.service.dto.situation.form.finalreport;

import com.kyonggi.Capstone_Develop.domain.situation.Final;
import lombok.Getter;

@Getter
public class FinalSaveResponseDto {
    private Long id;
    
    public FinalSaveResponseDto(final Long id) {
        this.id = id;
    }
    
    public static FinalSaveResponseDto from(final Final finalReport) {
        return new FinalSaveResponseDto(finalReport.getId());
    }
}
