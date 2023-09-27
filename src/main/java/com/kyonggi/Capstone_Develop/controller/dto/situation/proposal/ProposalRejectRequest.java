package com.kyonggi.Capstone_Develop.controller.dto.situation.proposal;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

import static com.kyonggi.Capstone_Develop.controller.dto.ValidateMessage.EMPTY_MESSAGE;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProposalRejectRequest {
    @NotBlank(message = EMPTY_MESSAGE)
    private String rejectReason;
}
