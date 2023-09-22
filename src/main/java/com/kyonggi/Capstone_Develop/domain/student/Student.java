package com.kyonggi.Capstone_Develop.domain.student;

import com.kyonggi.Capstone_Develop.domain.BaseEntity;
import com.kyonggi.Capstone_Develop.domain.Comment;
import com.kyonggi.Capstone_Develop.domain.graduation.Apply;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    
    @Column(name = "name", length = 255, nullable = false)
    private String name;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "sex", nullable = false)
    private Sex sex;
    
    @Column(name = "birth", nullable = false)
    private LocalDate birth;
    
    @Embedded
    private Email email;
    
    @Embedded
    private PhoneNumber phoneNumber;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "classification", length = 255, nullable = false)
    private Classification classification;

    @Column(name = "department", length = 255, nullable = false)
    private String department;
    
    @Column(name = "answerPw", length = 255, nullable = false)
    private String answerPw;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "roletype", length = 255, nullable = false)
    private RoleType roleType;
    
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<Apply> applies = new ArrayList<>();
    
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    public Student(
            String loginId,
            String password,
            LocalDate birth,
            String department,
            PhoneNumber phoneNumber,
            Sex sex,
            String name,
            Email email,
            RoleType roleType,
            String answerPw,
            Classification classification
    ) {
        this.loginId = loginId;
        this.password = password;
        this.birth = birth;
        this.department = department;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
        this.name = name;
        this.email = email;
        this.roleType = roleType;
        this.answerPw = answerPw;
        this.classification = classification;
    }
    
    public boolean isSameStudent(Long studentId) {
        return this.id.equals(studentId);
    }
    
    public void addApply(Apply apply) {
        this.applies.add(apply);
    }
}
