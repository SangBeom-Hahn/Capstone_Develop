package com.kyonggi.Capstone_Develop.domain.situation;

import com.kyonggi.Capstone_Develop.domain.BaseEntity;
import com.kyonggi.Capstone_Develop.domain.graduation.Apply;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "proposal_form", indexes = {
        @Index(name = "proposal_apply_index", columnList = "apply_id")
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Proposal extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proposal_form_id")
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
    
    @Column(name = "keyword", length = 45, nullable = false)
    private String keyword;
    
    @Column(name = "content", length = 255, nullable = false)
    private String content;
    
    @Column(name = "approval")
    @Enumerated(value = EnumType.STRING)
    private Approval approval;
    
    @Column(name = "reject_reason", length = 255, nullable = true)
    private String rejectReason;
    
    public Proposal(
            final Apply apply,
            final String title,
            final String division,
            final String qualification,
            final String keyword,
            final String content,
            final Approval approval,
            final String rejectReason
    ) {
        this.apply = apply;
        this.title = title;
        this.division = division;
        this.qualification = qualification;
        this.keyword = keyword;
        this.content = content;
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
