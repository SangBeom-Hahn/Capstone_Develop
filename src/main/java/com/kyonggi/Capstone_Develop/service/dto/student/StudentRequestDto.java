package com.kyonggi.Capstone_Develop.service.dto.student;

import com.kyonggi.Capstone_Develop.domain.student.Email;
import com.kyonggi.Capstone_Develop.domain.student.Grade;
import com.kyonggi.Capstone_Develop.domain.student.PhoneNumber;
import com.kyonggi.Capstone_Develop.domain.student.Sex;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class StudentRequestDto {
    private String loginId;
    
    private String password;
    
    private LocalDate birth;
    
    private String department;
    
    private Grade grade;
    
    private PhoneNumber phoneNumber;
    
    private Sex sex;
    
    private String name;
    
    private Email email;
    
    private String studentNumber;
}
