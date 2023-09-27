package com.kyonggi.Capstone_Develop.service.dto.situation;

import com.kyonggi.Capstone_Develop.domain.graduation.Graduation;
import com.kyonggi.Capstone_Develop.domain.graduation.Status;
import com.kyonggi.Capstone_Develop.domain.graduation.Step;
import com.kyonggi.Capstone_Develop.domain.student.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class SituationResponseDto {
    private String loginId;
    
    private String name;
    
    private String department;
    
    private String professorName;
    
    private LocalDate graduationDate;
    
    private Boolean capstoneCompletion;
    
    private Status status;
    
    private Step step;
    
    private List<Long> applyIds;
    
    public static SituationResponseDto of(Student student, Graduation graduation, List<Long> applyIds) {
        return new SituationResponseDto(
                student.getLoginId(),
                student.getName(),
                student.getDepartment(),
                graduation.getProfessorName(),
                graduation.getGraduationDate(),
                graduation.getCapstoneCompletion(),
                graduation.getStatus(),
                graduation.getStep(),
                applyIds
        );
    }
}
