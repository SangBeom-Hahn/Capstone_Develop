package com.kyonggi.Capstone_Develop.domain.student;

import com.kyonggi.Capstone_Develop.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Table(name = "student")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Enumerated(EnumType.STRING)
    @Column(name = "grade", nullable = false)
    private Grade grade;

    @Embedded
    private PhoneNumber phoneNumber;
    @Enumerated(EnumType.STRING)
    @Column(name = "sex", nullable = false)
    private Sex sex;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Embedded
    private Email email;

    @Column(name = "student_number", length = 255, nullable = false)
    private String studentNumber;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "roletype", length = 255, nullable = false)
    private RoleType roleType;

    public Student(String loginId, String password, LocalDate birth, String department, Grade grade, PhoneNumber phoneNumber, Sex sex, String name, Email email, String studentNumber, RoleType roleType) {
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
        this.roleType = roleType;
    }
}
