package com.kyonggi.Capstone_Develop.domain.situation;

import com.kyonggi.Capstone_Develop.domain.BaseEntity;
import com.kyonggi.Capstone_Develop.domain.graduation.Apply;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "final_form", indexes = {
        @Index(name = "final_apply_index", columnList = "apply_id")
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Final extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "final_form_id")
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "apply_id")
    private Apply apply;
    
    @Column(name = "title", length = 255, nullable = false)
    private String title;
    
    @Column(name = "division", length = 45, nullable = false)
    private String division;
    
    @Column(name = "qualification", length = 45, nullable = false)
    private String qualification;
    
    @Column(name = "page_number", nullable = false)
    private Integer pageNumber;
    
    @Column(name = "approval")
    @Enumerated(value = EnumType.STRING)
    private Approval approval;
    
    @Column(name = "reject_reason", length = 255, nullable = true)
    private String rejectReason;
    
    public Final(
            final Apply apply,
            final String title,
            final String division,
            final String qualification,
            final Integer pageNumber,
            final Approval approval,
            final String rejectReason
    ) {
        this.apply = apply;
        this.title = title;
        this.division = division;
        this.qualification = qualification;
        this.pageNumber = pageNumber;
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
