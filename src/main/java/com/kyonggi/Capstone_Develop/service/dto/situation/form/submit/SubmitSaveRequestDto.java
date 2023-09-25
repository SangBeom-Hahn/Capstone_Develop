package com.kyonggi.Capstone_Develop.service.dto.situation.form.submit;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class SubmitSaveRequestDto {
    private String professorName;
    
    private LocalDate graduationDate;
    
    private Boolean capstoneCompletion;
}
