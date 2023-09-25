package com.kyonggi.Capstone_Develop.domain.situation;

import com.kyonggi.Capstone_Develop.domain.BaseEntity;
import com.kyonggi.Capstone_Develop.domain.graduation.Apply;
import com.kyonggi.Capstone_Develop.exception.alreadyGraduateException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "submit_form")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Submit extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "submit_form_id")
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "apply_id")
    private Apply apply;
    
    @Column(name = "professor_name")
    private String professorName;
    
    @Column(name = "graduation_date")
    private LocalDate graduationDate;
    
    @Column(name = "capstone_completion")
    private Boolean capstoneCompletion;
    
    public Submit(Apply apply, String professorName, LocalDate graduationDate, Boolean capstoneCompletion) {
        validateGraduationDate(graduationDate);
        this.apply = apply;
        this.professorName = professorName;
        this.graduationDate = graduationDate;
        this.capstoneCompletion = capstoneCompletion;
    }
    
    private void validateGraduationDate(LocalDate graduationDate) {
        if (graduationDate.isBefore(LocalDate.now())) {
            throw new alreadyGraduateException();
        }
    }
}
