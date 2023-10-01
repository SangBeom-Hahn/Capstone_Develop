package com.kyonggi.Capstone_Develop.domain.graduation;

import com.kyonggi.Capstone_Develop.domain.BaseEntity;
import com.kyonggi.Capstone_Develop.exception.NoSuchApplyException;
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
    
    @Column(name = "capstone_completion")
    private Boolean capstoneCompletion;
    
    @Column(name = "graduation_date")
    private LocalDate graduationDate;
    
    @Column(name = "professor_name", length = 25)
    private String professorName;
    
    @OneToMany(mappedBy = "graduation", fetch = FetchType.LAZY)
    List<Apply> applies = new ArrayList<>();
    
    public Graduation(
            final Method method,
            final Status status,
            final Step step,
            final Boolean capstoneCompletion,
            final LocalDate graduationDate,
            final String professorName
    ) {
        this.method = method;
        this.status = status;
        this.step = step;
        this.capstoneCompletion = capstoneCompletion;
        this.graduationDate = graduationDate;
        this.professorName = professorName;
    }
    
    public Apply getApply() {
        validateApply();
        return this.applies.get(0);
    }
    
    private void validateApply() {
        if (applies.isEmpty()) {
            throw new NoSuchApplyException();
        }
    }
    
    public void addApply(final Apply apply) {
        this.applies.add(apply);
    }
    
    public void changeStep(final Step step) {
        this.step = step;
    }
    
    public void changeStatus(final Status status) {
        this.status = status;
    }
}
