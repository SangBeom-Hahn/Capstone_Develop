package com.kyonggi.Capstone_Develop.controller;

import com.kyonggi.Capstone_Develop.controller.auth.AuthenticationPrincipal;
import com.kyonggi.Capstone_Develop.controller.dto.GuidanceBoardUpdateRequest;
import com.kyonggi.Capstone_Develop.controller.dto.auth.LoginMemberRequest;
import com.kyonggi.Capstone_Develop.service.GuidanceBoardService;
import com.kyonggi.Capstone_Develop.service.dto.GuidanceBoardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class GuidanceBoardController {
    private final GuidanceBoardService guidanceBoardService;
    
    @GetMapping("/api/guidanceBoards")
    public ResponseEntity<GuidanceBoardResponseDto> findGuidanceBoard() {
        return ResponseEntity.ok(
                guidanceBoardService.findGuidanceBoard()
        );
    }
    
    @PutMapping("/api/admins/guidanceBoards")
    public ResponseEntity<Void> updateGuidanceBoard(
            @AuthenticationPrincipal LoginMemberRequest loginMemberRequest,
            @RequestBody @Valid GuidanceBoardUpdateRequest guidanceBoardUpdateRequest
    ) {
        guidanceBoardService.updateGuidanceBoard(guidanceBoardUpdateRequest.getContent());
        return ResponseEntity.noContent().build();
    }
}
