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

@Getter
@NoArgsConstructor
public class StudentRequest {
    @NotBlank(message = "비어있는 항목을 입력해주세요.")
    private String loginId;
    
    @NotBlank(message = "비어있는 항목을 입력해주세요.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&.])[A-Za-z[0-9]$@$!%*#?&.]{2,16}$",
            message = "2자 이상의 16자 이하의 숫자, 영문자, 특수문자를 포함한 비밀번호를 입력해주세요.")
    private String password;
    @NotNull(message = "비어있는 항목을 입력해주세요.")
    private LocalDate birth;
    
    @NotBlank(message = "비어있는 항목을 입력해주세요.")
    private String department;
    
    @NotBlank(message = "비어있는 항목을 입력해주세요.")
    private String grade;
    
    @NotNull(message = "비어있는 항목을 입력해주세요.")
    private String phoneNumber;
    
    @NotNull(message = "비어있는 항목을 입력해주세요.")
    private String sex;
    
    @NotBlank(message = "비어있는 항목을 입력해주세요.")
    private String name;
    
    @NotNull(message = "비어있는 항목을 입력해주세요.")
    private String email;
    
    @NotBlank(message = "비어있는 항목을 입력해주세요.")
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
