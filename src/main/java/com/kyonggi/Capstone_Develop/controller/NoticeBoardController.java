package com.kyonggi.Capstone_Develop.controller;

import com.kyonggi.Capstone_Develop.controller.auth.AuthenticationPrincipal;
import com.kyonggi.Capstone_Develop.controller.dto.auth.LoginMemberRequest;
import com.kyonggi.Capstone_Develop.controller.dto.noticeboard.NoticeBoardSaveRequest;
import com.kyonggi.Capstone_Develop.controller.dto.noticeboard.NoticeBoardUpdateRequest;
import com.kyonggi.Capstone_Develop.service.NoticeBoardService;
import com.kyonggi.Capstone_Develop.service.dto.noticeboard.NoticeBoardResponseDto;
import com.kyonggi.Capstone_Develop.service.dto.noticeboard.NoticeBoardSaveResponseDto;
import com.kyonggi.Capstone_Develop.service.dto.noticeboard.NoticeBoardsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
public class NoticeBoardController {
    private final NoticeBoardService noticeBoardService;
    
    @PostMapping("/api/admins/noticeboards")
    public ResponseEntity<NoticeBoardSaveResponseDto> createNoticeBoard(
            @AuthenticationPrincipal final LoginMemberRequest loginMemberRequest,
            @RequestBody @Valid final NoticeBoardSaveRequest noticeBoardSaveRequest
    ) {
        NoticeBoardSaveResponseDto noticeBoardSaveResponseDto =
                noticeBoardService.save(noticeBoardSaveRequest.toServiceDto(), loginMemberRequest.getId());
        return ResponseEntity
                .created(URI.create("/api/admins/noticeboards/" + noticeBoardSaveResponseDto.getId()))
                .body(noticeBoardSaveResponseDto);
    }
    
    @GetMapping("/api/noticeboards/{noticeBoardId}")
    public ResponseEntity<NoticeBoardResponseDto> findNoticeBoard(
        @AuthenticationPrincipal final LoginMemberRequest loginMemberRequest,
        @PathVariable final Long noticeBoardId
    ) {
        NoticeBoardResponseDto noticeBoardResponse =
                noticeBoardService.findNoticeBoard(noticeBoardId);
        return ResponseEntity.ok(noticeBoardResponse);
    }
    
    @GetMapping("/api/noticeboards")
    public ResponseEntity<NoticeBoardsResponseDto> findAllNoticeBoard(
            @AuthenticationPrincipal final LoginMemberRequest loginMemberRequest,
            @RequestParam("page") final Integer page,
            @RequestParam("count") final int count
    ) {
        return ResponseEntity.ok(
                noticeBoardService.findAllNoticeBoard(page, count)
        );
    }
    
    @PutMapping("/api/admins/noticeboards/{noticeBoardId}")
    public ResponseEntity<Void> updateNoticeBoard(
            @AuthenticationPrincipal final LoginMemberRequest loginMemberRequest,
            @RequestBody @Valid final NoticeBoardUpdateRequest noticeBoardUpdateRequest,
            @PathVariable final Long noticeBoardId
    ) {
        noticeBoardService.updateNoticeBoard(
                noticeBoardUpdateRequest.toServiceDto(),
                noticeBoardId
        );
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/api/admins/noticeboards/{noticeBoardId}")
    public ResponseEntity<Void> deleteNoticeBoard(
            @AuthenticationPrincipal final LoginMemberRequest loginMemberRequest,
            @PathVariable final Long noticeBoardId
    ) {
        noticeBoardService.deleteNoticeBoard(noticeBoardId, loginMemberRequest.getId());
        return ResponseEntity.noContent().build();
    }
}
