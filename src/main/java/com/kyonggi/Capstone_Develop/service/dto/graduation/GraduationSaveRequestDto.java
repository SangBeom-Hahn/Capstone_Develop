package com.kyonggi.Capstone_Develop.service.dto.graduation;

import com.kyonggi.Capstone_Develop.domain.graduation.Method;
import com.kyonggi.Capstone_Develop.domain.graduation.Status;
import com.kyonggi.Capstone_Develop.domain.graduation.Step;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class GraduationSaveRequestDto {
    private Method method;
    
//    private Status status;
//
//    private Step step;
    
//    private Boolean capstoneCompletion;
//
//    private LocalDate graduationDate;
//
//    private String professorName;
}
