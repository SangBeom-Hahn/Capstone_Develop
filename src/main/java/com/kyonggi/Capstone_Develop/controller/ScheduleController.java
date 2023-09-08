package com.kyonggi.Capstone_Develop.controller;

import com.kyonggi.Capstone_Develop.controller.auth.AuthenticationPrincipal;
import com.kyonggi.Capstone_Develop.controller.dto.auth.LoginMemberRequest;
import com.kyonggi.Capstone_Develop.service.dto.schedule.AllScheduleResponseDto;
import com.kyonggi.Capstone_Develop.controller.dto.scheduleboard.ScheduleBoardUpdateRequest;
import com.kyonggi.Capstone_Develop.controller.dto.scheduleboard.ScheduleUpdateRequest;
import com.kyonggi.Capstone_Develop.service.ScheduleBoardService;
import com.kyonggi.Capstone_Develop.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;
    
    private final ScheduleBoardService scheduleBoardService;
    
    @GetMapping("/api/schedules")
    public ResponseEntity<AllScheduleResponseDto> findAllSchedule(@AuthenticationPrincipal LoginMemberRequest loginMemberRequest) {
        return ResponseEntity.ok(scheduleService.findAllSchedule());
    }
    
    @PutMapping("/api/admins/schedules/{scheduleId}")
    public ResponseEntity<Void> updateSchedule(
            @AuthenticationPrincipal LoginMemberRequest loginMemberRequest,
            @RequestBody @Valid ScheduleUpdateRequest scheduleUpdateRequest,
            @PathVariable Long scheduleId
    ) {
        scheduleService.updateSchedule(
                scheduleId,
                scheduleUpdateRequest.getStartDate(),
                scheduleUpdateRequest.getEndDate()
        );
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/api/admins/scheduleboards")
    public ResponseEntity<Void> updateScheduleBoard(
            @AuthenticationPrincipal LoginMemberRequest loginMemberRequest,
            @RequestBody @Valid ScheduleBoardUpdateRequest scheduleBoardUpdateRequest
    ) {
        scheduleBoardService.updateScheduleBoard(scheduleBoardUpdateRequest.toServiceDto());
        return ResponseEntity.noContent().build();
    }
}
