package com.kyonggi.Capstone_Develop.service.dto.graduation;

import com.kyonggi.Capstone_Develop.domain.graduation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class GraduationResponseDto {
    private Long id;
    
    private Method method;
    
    private Status status;
    
    private Step step;
    
    private Boolean capstoneCompletion;
    
    private LocalDate graduationDate;
    
    private String professorName;
    
    private String studentLoginId;
    
    private String studentName;
    
    public static GraduationResponseDto of(Graduation graduation, Apply apply) {
        return new GraduationResponseDto(
                graduation.getId(),
                graduation.getMethod(),
                graduation.getStatus(),
                graduation.getStep(),
                graduation.getCapstoneCompletion(),
                graduation.getGraduationDate(),
                graduation.getProfessorName(),
                apply.getStudentLoginId(),
                apply.getStudentName()
        );
    }
}
