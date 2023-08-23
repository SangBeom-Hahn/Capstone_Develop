package com.kyonggi.Capstone_Develop.controller.dto.student;

import com.kyonggi.Capstone_Develop.domain.student.Email;
import com.kyonggi.Capstone_Develop.domain.student.Grade;
import com.kyonggi.Capstone_Develop.domain.student.PhoneNumber;
import com.kyonggi.Capstone_Develop.domain.student.Sex;
import com.kyonggi.Capstone_Develop.service.dto.student.StudentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

import static com.kyonggi.Capstone_Develop.controller.dto.ValidateMessage.*;

@Getter
@NoArgsConstructor
public class StudentRequest {
    @NotBlank(message = EMPTY_MESSAGE)
    private String loginId;
    
    @NotBlank(message = EMPTY_MESSAGE)
    @Pattern(regexp = PASSWORD_FORMAT,
            message = MEMBER_PW_MESSAGE)
    private String password;
    @NotNull(message = EMPTY_MESSAGE)
    private LocalDate birth;
    
    @NotBlank(message = EMPTY_MESSAGE)
    private String department;
    
    @NotBlank(message = EMPTY_MESSAGE)
    private String grade;
    
    @NotNull(message = EMPTY_MESSAGE)
    private String phoneNumber;
    
    @NotNull(message = EMPTY_MESSAGE)
    private String sex;
    
    @NotBlank(message = EMPTY_MESSAGE)
    private String name;
    
    @NotNull(message = EMPTY_MESSAGE)
    private String email;
    
    @NotBlank(message = EMPTY_MESSAGE)
    private String studentNumber;
    
    public StudentRequestDto toServiceDto() {
        return new StudentRequestDto(
                loginId,
                password,
                birth,
                department,
                Grade.valueOf(grade),
                PhoneNumber.from(phoneNumber),
                Sex.valueOf(sex),
                name,
                Email.from(email),
                studentNumber
        );
    }
}
