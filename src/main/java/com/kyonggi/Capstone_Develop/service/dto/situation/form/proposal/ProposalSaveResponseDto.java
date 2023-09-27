package com.kyonggi.Capstone_Develop.service.dto.situation.form.proposal;

import com.kyonggi.Capstone_Develop.domain.situation.Proposal;
import lombok.Getter;

@Getter
public class ProposalSaveResponseDto {
    private Long id;
    
    public ProposalSaveResponseDto(Long id) {
        this.id = id;
    }
    
    public static ProposalSaveResponseDto from(Proposal proposal) {
        return new ProposalSaveResponseDto(proposal.getId());
    }
}
