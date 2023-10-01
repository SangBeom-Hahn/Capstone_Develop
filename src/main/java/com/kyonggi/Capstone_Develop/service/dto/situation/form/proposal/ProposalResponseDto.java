package com.kyonggi.Capstone_Develop.service.dto.situation.form.proposal;

import com.kyonggi.Capstone_Develop.domain.graduation.Apply;
import com.kyonggi.Capstone_Develop.domain.situation.Proposal;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProposalResponseDto {
    private Long proposalId;
    
    private Long applyId;
    
    private String title;
    
    private String division;
    
    private String keyword;
    
    private String content;
    
    private String rejectReason;
    
    public static ProposalResponseDto of(final Proposal proposal, final Apply apply) {
        return new ProposalResponseDto(
                proposal.getId(),
                apply.getId(),
                proposal.getTitle(),
                proposal.getDivision(),
                proposal.getKeyword(),
                proposal.getContent(),
                proposal.getRejectReason()
        );
    }
}
