package com.kyonggi.Capstone_Develop.domain.graduation;

import com.kyonggi.Capstone_Develop.domain.BaseEntity;
import com.kyonggi.Capstone_Develop.domain.student.Student;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "apply" , indexes = {
        @Index(name = "apply_student_index", columnList = "student_id")
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Apply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apply_id")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", foreignKey = @ForeignKey(name = "fk_apply_student"), nullable = false)
    private Student student;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "graduation_id", foreignKey = @ForeignKey(name = "fk_apply_graduation"), nullable = false)
    private Graduation graduation;
    
    public Apply(Student student, Graduation graduation) {
        this.student = student;
        this.graduation = graduation;
        this.student.addApply(this);
        this.graduation.addApply(this);
    }
    
    public String getStudentName() {
        return this.student.getName();
    }
    
    public String getStudentLoginId() {
        return this.student.getLoginId();
    }
}
