package com.kyonggi.Capstone_Develop.domain.graduation;

import com.kyonggi.Capstone_Develop.domain.BaseEntity;
import com.kyonggi.Capstone_Develop.exception.alreadyGraduateException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "graduation")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Graduation extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "graduation_id")
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "method",  nullable = false)
    private Method method;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "step", nullable = false)
    private Step step;
    
    @Column(name = "capstone_completion", nullable = false)
    private Boolean capstoneCompletion;
    
    @Column(name = "graduation_date", nullable = false)
    private LocalDate graduationDate;
    
    @Column(name = "professor_name", length = 25, nullable = false)
    private String professorName;
    
    @OneToMany(mappedBy = "graduation", fetch = FetchType.LAZY)
    List<Apply> applies = new ArrayList<>();
    
    public Graduation(
            Method method,
            Status status,
            Step step,
            Boolean capstoneCompletion,
            LocalDate graduationDate,
            String professorName
    ) {
        validateGraduationDate(graduationDate);
        this.method = method;
        this.status = status;
        this.step = step;
        this.capstoneCompletion = capstoneCompletion;
        this.graduationDate = graduationDate;
        this.professorName = professorName;
    }
    
    private void validateGraduationDate(LocalDate graduationDate) {
        if (graduationDate.isBefore(LocalDate.now())) {
            throw new alreadyGraduateException();
        }
    }
    
    public void addApply(Apply apply) {
        this.applies.add(apply);
    }
}
