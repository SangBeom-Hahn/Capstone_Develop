package com.kyonggi.Capstone_Develop.controller;

import com.kyonggi.Capstone_Develop.controller.auth.AuthenticationPrincipal;
import com.kyonggi.Capstone_Develop.controller.dto.auth.LoginMemberRequest;
import com.kyonggi.Capstone_Develop.service.SituationService;
import com.kyonggi.Capstone_Develop.service.dto.situation.SituationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/situations")
@RequiredArgsConstructor
public class SituationController {
    private final SituationService situationService;
    
    @GetMapping("/{studentId}")
    public ResponseEntity<SituationResponseDto> findSituation(
            @AuthenticationPrincipal LoginMemberRequest loginMemberRequest,
            @PathVariable Long studentId
    ) {
        SituationResponseDto situationResponseDto = situationService.findSituation(studentId);
        return ResponseEntity.ok(situationResponseDto);
    }
}
