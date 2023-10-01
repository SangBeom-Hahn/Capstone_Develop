package com.kyonggi.Capstone_Develop.domain.situation;

import com.kyonggi.Capstone_Develop.domain.BaseEntity;
import com.kyonggi.Capstone_Develop.domain.graduation.Apply;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "interim_form", indexes = {
        @Index(name = "interim_apply_index", columnList = "apply_id")
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Interim extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interim_form_id")
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "apply_id")
    private Apply apply;
    
    @Column(name = "title", length = 255, nullable = false)
    private String title;
    
    @Column(name = "division", length = 45, nullable = false)
    private String division;
    
    @Column(name = "text", length = 255, nullable = false)
    private String text;
    
    @Column(name = "plan", length = 255, nullable = false)
    private String plan;
    
    @Column(name = "approval")
    @Enumerated(value = EnumType.STRING)
    private Approval approval;
    
    @Column(name = "reject_reason", length = 255, nullable = true)
    private String rejectReason;
    
    public Interim(
            final Apply apply,
            final String title,
            final String division,
            final String text,
            final String plan,
            final Approval approval,
            final String rejectReason
    ) {
        this.apply = apply;
        this.title = title;
        this.division = division;
        this.text = text;
        this.plan = plan;
        this.approval = approval;
        this.rejectReason = rejectReason;
    }
    
    public void changeApproval(final Approval approval) {
        this.approval = approval;
    }
    
    public void changeRejectReason(final String rejectReason) {
        this.rejectReason = rejectReason;
    }
}
