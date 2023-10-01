package com.kyonggi.Capstone_Develop.controller;

import com.kyonggi.Capstone_Develop.controller.auth.AuthenticationPrincipal;
import com.kyonggi.Capstone_Develop.controller.dto.auth.LoginMemberRequest;
import com.kyonggi.Capstone_Develop.controller.dto.graduation.GraduationSaveRequest;
import com.kyonggi.Capstone_Develop.service.GraduationService;
import com.kyonggi.Capstone_Develop.service.dto.graduation.GraduationSaveResponseDto;
import com.kyonggi.Capstone_Develop.service.dto.graduation.GraduationsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
public class GraduationController {
    private final GraduationService graduationService;
    
    @PostMapping("/api/graduations")
    public ResponseEntity<GraduationSaveResponseDto> create(
            @RequestBody @Valid final GraduationSaveRequest graduationSaveRequest,
            @AuthenticationPrincipal final LoginMemberRequest loginMemberRequest
    ) {
        GraduationSaveResponseDto graduationSaveResponseDto =
                graduationService.save(graduationSaveRequest.toServiceDto(), loginMemberRequest.getId());
        return ResponseEntity
                .created(URI.create("/api/graduations" + graduationSaveResponseDto.getId()))
                .body(graduationSaveResponseDto);
    }
    
    @GetMapping("/api/admins/graduations")
    public ResponseEntity<GraduationsResponseDto> findAllGraduation(@AuthenticationPrincipal final LoginMemberRequest loginMemberRequest) {
        return ResponseEntity.ok(
                graduationService.findAllGraduation()
        );
    }
}
