package com.kyonggi.Capstone_Develop.service.dto.situation.form.finalreport;

import com.kyonggi.Capstone_Develop.domain.situation.Final;
import com.kyonggi.Capstone_Develop.domain.situation.Proposal;
import com.kyonggi.Capstone_Develop.service.dto.situation.form.proposal.ProposalSaveResponseDto;
import lombok.Getter;

@Getter
public class FinalSaveResponseDto {
    private Long id;
    
    public FinalSaveResponseDto(Long id) {
        this.id = id;
    }
    
    public static FinalSaveResponseDto from(Final finalReport) {
        return new FinalSaveResponseDto(finalReport.getId());
    }
}
