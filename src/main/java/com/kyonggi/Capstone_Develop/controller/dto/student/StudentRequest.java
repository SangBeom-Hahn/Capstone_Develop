package com.kyonggi.Capstone_Develop.controller.dto.student;

import com.kyonggi.Capstone_Develop.domain.student.Classification;
import com.kyonggi.Capstone_Develop.domain.student.Email;
import com.kyonggi.Capstone_Develop.domain.student.PhoneNumber;
import com.kyonggi.Capstone_Develop.domain.student.Sex;
import com.kyonggi.Capstone_Develop.service.dto.student.StudentRequestDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

import static com.kyonggi.Capstone_Develop.controller.dto.ValidateMessage.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudentRequest {
    @NotBlank(message = EMPTY_MESSAGE)
    private String studentId;
    
    @NotBlank(message = EMPTY_MESSAGE)
    @Pattern(regexp = PASSWORD_FORMAT,
            message = MEMBER_PW_MESSAGE)
    private String studentPassword;
    
    @NotBlank(message = EMPTY_MESSAGE)
    private String studentName;
    
    @NotBlank(message = EMPTY_MESSAGE)
    private String sex;
    @NotNull(message = EMPTY_MESSAGE)
    private LocalDate birth;
    
    @NotBlank(message = EMPTY_MESSAGE)
    private String email;
    
    @NotBlank(message = EMPTY_MESSAGE)
    private String phoneNumber;
    
    @NotBlank(message = EMPTY_MESSAGE)
    private String classification;
    
    @NotBlank(message = EMPTY_MESSAGE)
    private String department;
    
    @NotBlank(message = EMPTY_MESSAGE)
    private String answerPw;
    
    public StudentRequestDto toServiceDto() {
        return new StudentRequestDto(
                studentId,
                studentPassword,
                studentName,
                Sex.from(sex),
                birth,
                Email.from(email),
                PhoneNumber.from(phoneNumber),
                Classification.from(classification),
                department,
                answerPw
        );
    }
}
