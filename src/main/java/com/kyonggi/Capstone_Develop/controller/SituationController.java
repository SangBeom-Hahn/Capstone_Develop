package com.kyonggi.Capstone_Develop.controller;

import com.kyonggi.Capstone_Develop.controller.auth.AuthenticationPrincipal;
import com.kyonggi.Capstone_Develop.controller.dto.auth.LoginMemberRequest;
import com.kyonggi.Capstone_Develop.controller.dto.situation.submit.SubmitSaveRequest;
import com.kyonggi.Capstone_Develop.service.SituationService;
import com.kyonggi.Capstone_Develop.service.dto.situation.SituationResponseDto;
import com.kyonggi.Capstone_Develop.service.dto.situation.form.submit.SubmitResponseDto;
import com.kyonggi.Capstone_Develop.service.dto.situation.form.submit.SubmitSaveResponseDto;
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
}
