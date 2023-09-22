package com.kyonggi.Capstone_Develop.service.dto.student;

import com.kyonggi.Capstone_Develop.domain.student.Classification;
import com.kyonggi.Capstone_Develop.domain.student.Email;
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
    
    private String name;
    
    private Sex sex;
    
    private LocalDate birth;
    
    private Email email;
    
    private PhoneNumber phoneNumber;
    
    private Classification classification;
    
    private String department;
    
    private String answerPw;
}
