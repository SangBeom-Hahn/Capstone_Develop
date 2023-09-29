package com.kyonggi.Capstone_Develop.service.dto.situation.form.finalreport;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FinalSaveRequestDto {
    private String title;
    
    private String division;
    
    private String qualification;
    
    private Integer pageNumber;
}
