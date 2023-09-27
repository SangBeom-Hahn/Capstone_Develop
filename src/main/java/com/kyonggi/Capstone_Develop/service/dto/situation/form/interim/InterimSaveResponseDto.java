package com.kyonggi.Capstone_Develop.service.dto.situation.form.interim;

import com.kyonggi.Capstone_Develop.domain.situation.Interim;
import com.kyonggi.Capstone_Develop.domain.situation.Proposal;
import com.kyonggi.Capstone_Develop.service.dto.situation.form.proposal.ProposalSaveResponseDto;
import lombok.Getter;

@Getter
public class InterimSaveResponseDto {
    private Long id;
    
    public InterimSaveResponseDto(Long id) {
        this.id = id;
    }
    
    public static InterimSaveResponseDto from(Interim interim) {
        return new InterimSaveResponseDto(interim.getId());
    }
}
