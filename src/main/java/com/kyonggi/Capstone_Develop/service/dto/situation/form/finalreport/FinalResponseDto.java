package com.kyonggi.Capstone_Develop.service.dto.situation.form.finalreport;

import com.kyonggi.Capstone_Develop.domain.graduation.Apply;
import com.kyonggi.Capstone_Develop.domain.situation.Final;
import com.kyonggi.Capstone_Develop.domain.situation.Proposal;
import com.kyonggi.Capstone_Develop.service.dto.situation.form.proposal.ProposalResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FinalResponseDto {
    private Long proposalId;
    
    private Long applyId;
    
    private String title;
    
    private String division;
    
    private String qualification;
    
    private Integer pageNumber;
    
    private String rejectReason;
    
    public static FinalResponseDto of(Final finalReport, Apply apply) {
        return new FinalResponseDto(
                finalReport.getId(),
                apply.getId(),
                finalReport.getTitle(),
                finalReport.getDivision(),
                finalReport.getQualification(),
                finalReport.getPageNumber(),
                finalReport.getRejectReason()
        );
    }
}
