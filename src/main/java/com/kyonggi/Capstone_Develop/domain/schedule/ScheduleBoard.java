package com.kyonggi.Capstone_Develop.domain.schedule;

import com.kyonggi.Capstone_Develop.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "schedule_board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleBoard extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_board_id")
    private Long id;
    
    @Column(name = "receive", nullable = false)
    private String receive;
    
    @Column(name = "proposal", nullable = false)
    private String proposal;
    
    @Column(name = "middle_report", nullable = false)
    private String middleReport;
    
    @Column(name = "final_report", nullable = false)
    private String finalReport;
    
    @Column(name = "final_pass", nullable = false)
    private String finalPass;
    
    @Column(name = "other_qualification", nullable = false)
    private String otherQualification;
    
    public ScheduleBoard(
            String receive,
            String proposal,
            String middleReport,
            String finalReport,
            String finalPass,
            String otherQualification
    ) {
        this.receive = receive;
        this.proposal = proposal;
        this.middleReport = middleReport;
        this.finalReport = finalReport;
        this.finalPass = finalPass;
        this.otherQualification = otherQualification;
    }
    
    public void changeReceive(String receive) {
        this.receive = receive;
    }
    
    public void changeProposal(String proposal) {
        this.proposal = proposal;
    }
    
    public void changeMiddleReport(String middleReport) {
        this.middleReport = middleReport;
    }
    
    public void changeFinalReport(String finalReport) {
        this.finalReport = finalReport;
    }
    
    public void changeFinalPass(String finalPass) {
        this.finalPass = finalPass;
    }
    
    public void changeOtherQualification(String otherQualification) {
        this.otherQualification = otherQualification;
    }
}
