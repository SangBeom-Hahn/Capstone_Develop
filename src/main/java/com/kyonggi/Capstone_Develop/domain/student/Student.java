package com.kyonggi.Capstone_Develop.domain.student;

import com.kyonggi.Capstone_Develop.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Getter
@Table(name = "student")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Student extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;
    
    @Column(name = "login_id", length = 255, nullable = false)
    private String loginId;
    
    @Column(name = "password", length = 255, nullable = false)
    private String password;
    
    @Column(name = "birth", nullable = false)
    private LocalDate birth;
    
    @Column(name = "department", length = 255, nullable = false)
    private String department;
    
    @Column(name = "grade", nullable = false)
    private Grade grade;
    
    @Column(name = "phone_number", length = 255, nullable = false)
    private String phoneNumber;
    
    @Column(name = "sex", nullable = false)
    private Sex sex;
    
    @Column(name = "name", length = 255, nullable = false)
    private String name;
    
    @Column(name = "email", length = 255, nullable = false)
    private String email;
    
    @Column(name = "student_number", length = 255, nullable = false)
    private String studentNumber;
    
    public Student(String loginId, String password, LocalDate birth, String department, Grade grade, String phoneNumber, Sex sex, String name, String email, String studentNumber) {
        validateEmail(email);
        validatePhoneNumber(phoneNumber);
        validateLoginId(loginId);
        this.loginId = loginId;
        this.password = password;
        this.birth = birth;
        this.department = department;
        this.grade = grade;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
        this.name = name;
        this.email = email;
        this.studentNumber = studentNumber;
    }
    
    private void validateEmail(String email) {
        Pattern pattern = Pattern.compile("^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$");
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(email);
        }
    }
    
    private void validateLoginId(String loginId) {
        validateLoginIdSize(loginId);
        validateLoginIdHasSpecialCharacter(loginId);
    }
    
    private void validateLoginIdSize(String loginId) {
        if (isValidSize(loginId)) {
            throw new IllegalArgumentException(loginId);
        }
    }
    
    private static boolean isValidSize(String loginId) {
        return loginId.length() < 2 && loginId.length() > 16;
    }
    
    private void validateLoginIdHasSpecialCharacter(String loginId) {
        Pattern pattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&.])[A-Za-z[0-9]$@$!%*#?&.]{0,7}$");
        Matcher matcher = pattern.matcher(loginId);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(loginId);
        }
    }
    
    private void validatePhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(phoneNumber);
        }
    }
}
