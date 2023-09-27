package com.kyonggi.Capstone_Develop.service.dto.situation.form.proposal;

import com.kyonggi.Capstone_Develop.domain.situation.Proposal;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProposalRejectResponseDto {
    private Long id;
    
    private String title;
    
    private String division;
    
    private String keyword;
    
    private String content;
    
    public static ProposalRejectResponseDto from(Proposal proposal) {
        return new ProposalRejectResponseDto(
                proposal.getId(),
                proposal.getTitle(),
                proposal.getDivision(),
                proposal.getKeyword(),
                proposal.getContent()
        );
    }
}
