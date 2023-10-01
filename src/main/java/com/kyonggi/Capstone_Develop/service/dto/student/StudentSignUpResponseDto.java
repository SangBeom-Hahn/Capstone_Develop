package com.kyonggi.Capstone_Develop.service.dto.student;

import com.kyonggi.Capstone_Develop.domain.student.Student;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class StudentSignUpResponseDto {
    
    private Long id;
    
    public static StudentSignUpResponseDto from(final Student student) {
        return new StudentSignUpResponseDto(student.getId());
    }
}
