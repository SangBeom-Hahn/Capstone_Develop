package com.kyonggi.Capstone_Develop.controller;

import com.kyonggi.Capstone_Develop.controller.auth.AuthenticationPrincipal;
import com.kyonggi.Capstone_Develop.controller.dto.auth.LoginMemberRequest;
import com.kyonggi.Capstone_Develop.controller.dto.situation.SubmitSaveRequest;
import com.kyonggi.Capstone_Develop.controller.dto.situation.interim.InterimRejectRequest;
import com.kyonggi.Capstone_Develop.controller.dto.situation.interim.InterimSaveRequest;
import com.kyonggi.Capstone_Develop.controller.dto.situation.proposal.ProposalRejectRequest;
import com.kyonggi.Capstone_Develop.controller.dto.situation.proposal.ProposalSaveRequest;
import com.kyonggi.Capstone_Develop.service.SituationService;
import com.kyonggi.Capstone_Develop.service.dto.situation.SituationResponseDto;
import com.kyonggi.Capstone_Develop.service.dto.situation.form.interim.InterimSaveResponseDto;
import com.kyonggi.Capstone_Develop.service.dto.situation.form.proposal.ProposalSaveResponseDto;
import com.kyonggi.Capstone_Develop.service.dto.situation.form.submit.SubmitResponseDto;
import com.kyonggi.Capstone_Develop.service.dto.situation.form.submit.SubmitSaveResponseDto;
import com.kyonggi.Capstone_Develop.service.situation.InterimService;
import com.kyonggi.Capstone_Develop.service.situation.ProposalService;
import com.kyonggi.Capstone_Develop.service.situation.SubmitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
public class SituationController {
    private final SituationService situationService;
    
    private final SubmitService submitService;
    
    private final ProposalService proposalService;
    
    private final InterimService interimService;
    
    @GetMapping("/api/situations/{studentId}")
    public ResponseEntity<SituationResponseDto> findSituation(
            @AuthenticationPrincipal LoginMemberRequest loginMemberRequest,
            @PathVariable Long studentId
    ) {
        SituationResponseDto situationResponseDto = situationService.findSituation(studentId);
        return ResponseEntity.ok(situationResponseDto);
    }
    
    @PostMapping("/api/admins/situations/submit/{studentId}")
    public ResponseEntity<SubmitSaveResponseDto> createSubmit(
            @AuthenticationPrincipal LoginMemberRequest loginMemberRequest,
            @PathVariable Long studentId,
            @RequestBody @Valid SubmitSaveRequest submitSaveRequest
    ) {
        SubmitSaveResponseDto submitSaveResponseDto =
                submitService.saveSubmit(submitSaveRequest.toServiceDto(), studentId);
        return ResponseEntity
                .created(URI.create("/api/admins/situations/submit/" + submitSaveResponseDto.getId()))
                .body(submitSaveResponseDto);
    }
    
    @GetMapping("/api/situations/submit/{applyId}")
    public ResponseEntity<SubmitResponseDto> findSubmit(
            @AuthenticationPrincipal LoginMemberRequest loginMemberRequest,
            @PathVariable Long applyId
    ) {
        SubmitResponseDto submitResponseDto = submitService.findSubmit(applyId);
        return ResponseEntity.ok(submitResponseDto);
    }
    
    @PostMapping("/api/situations/proposal/{studentId}")
    public ResponseEntity<ProposalSaveResponseDto> createProposal(
            @AuthenticationPrincipal LoginMemberRequest loginMemberRequest,
            @PathVariable Long studentId,
            @RequestBody @Valid ProposalSaveRequest proposalSaveRequest
    ) {
        ProposalSaveResponseDto proposalSaveResponseDto =
                proposalService.saveProposal(proposalSaveRequest.toServiceDto(), studentId);
        return ResponseEntity
                .created(URI.create("/api/admins/situations/proposal/" + proposalSaveResponseDto.getId()))
                .body(proposalSaveResponseDto);
    }
    
    @PutMapping("/api/admins/situations/proposal/approve/{applyId}")
    public ResponseEntity<Void> approveProposal(
            @AuthenticationPrincipal LoginMemberRequest loginMemberRequest,
            @PathVariable Long applyId
    ) {
        proposalService.approveProposal(applyId);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/api/admins/situations/proposal/reject/{applyId}")
    public ResponseEntity<Void> rejectProposal(
            @AuthenticationPrincipal LoginMemberRequest loginMemberRequest,
            @PathVariable Long applyId,
            @RequestBody @Valid ProposalRejectRequest proposalRejectRequest
    ) {
        proposalService.rejectProposal(applyId, proposalRejectRequest.getRejectReason());
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/api/situations/interim/{studentId}")
    public ResponseEntity<InterimSaveResponseDto> createInterim(
            @AuthenticationPrincipal LoginMemberRequest loginMemberRequest,
            @PathVariable Long studentId,
            @RequestBody @Valid InterimSaveRequest interimSaveRequest
    ) {
        InterimSaveResponseDto interimSaveResponseDto =
                interimService.saveInterim(interimSaveRequest.toServiceDto(), studentId);
        return ResponseEntity
                .created(URI.create("/api/admins/situations/interim/" + interimSaveResponseDto.getId()))
                .body(interimSaveResponseDto);
    }
    
    @PutMapping("/api/admins/situations/interim/approve/{applyId}")
    public ResponseEntity<Void> approveInterim(
            @AuthenticationPrincipal LoginMemberRequest loginMemberRequest,
            @PathVariable Long applyId
    ) {
        interimService.approveInterim(applyId);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/api/admins/situations/interim/reject/{applyId}")
    public ResponseEntity<Void> rejectInterim(
            @AuthenticationPrincipal LoginMemberRequest loginMemberRequest,
            @PathVariable Long applyId,
            @RequestBody @Valid InterimRejectRequest interimRejectRequest
    ) {
        interimService.rejectInterim(applyId, interimRejectRequest.getRejectReason());
        return ResponseEntity.noContent().build();
    }
}
