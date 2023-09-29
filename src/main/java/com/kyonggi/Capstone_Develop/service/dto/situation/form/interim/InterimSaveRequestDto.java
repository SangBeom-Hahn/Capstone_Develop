package com.kyonggi.Capstone_Develop.service.dto.situation.form.interim;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InterimSaveRequestDto {
    private String title;
    
    private String division;
    
    private String text;
    
    private String plan;
}
