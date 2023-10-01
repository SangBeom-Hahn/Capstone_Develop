package com.kyonggi.Capstone_Develop.service.dto.situation.form.submit;

import com.kyonggi.Capstone_Develop.domain.situation.Submit;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class SubmitResponseDto {
    private Long id;
    
    private String professorName;
    
    private LocalDate graduationDate;
    
    private Boolean capstoneCompletion;
    
    public static SubmitResponseDto from(final Submit submit) {
        return new SubmitResponseDto(
                submit.getId(),
                submit.getProfessorName(),
                submit.getGraduationDate(),
                submit.getCapstoneCompletion()
        );
    }
}
