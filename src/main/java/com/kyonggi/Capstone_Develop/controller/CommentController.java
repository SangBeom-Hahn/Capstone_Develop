package com.kyonggi.Capstone_Develop.controller;

import com.kyonggi.Capstone_Develop.controller.auth.AuthenticationPrincipal;
import com.kyonggi.Capstone_Develop.controller.dto.auth.LoginMemberRequest;
import com.kyonggi.Capstone_Develop.controller.dto.comment.CommentSaveRequest;
import com.kyonggi.Capstone_Develop.controller.dto.comment.CommentUpdateRequest;
import com.kyonggi.Capstone_Develop.service.CommentService;
import com.kyonggi.Capstone_Develop.service.dto.comment.CommentSaveResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    
    @PostMapping("/{noticeBoardId}")
    public ResponseEntity<CommentSaveResponseDto> createComment(
            @AuthenticationPrincipal final LoginMemberRequest loginMemberRequest,
            @RequestBody @Valid final CommentSaveRequest commentSaveRequest,
            @PathVariable final Long noticeBoardId
    ) {
        CommentSaveResponseDto commentSaveResponseDto = commentService.save(
                noticeBoardId,
                loginMemberRequest.getId(),
                commentSaveRequest.getContent()
        );
        return ResponseEntity
                .created(URI.create("/api/comments/" + commentSaveResponseDto.getId()))
                .body(commentSaveResponseDto);
    }
    
    @PutMapping("/{commentId}")
    public ResponseEntity<Void> updateComment(
            @AuthenticationPrincipal final LoginMemberRequest loginMemberRequest,
            @RequestBody @Valid final CommentUpdateRequest commentUpdateRequest,
            @PathVariable final Long commentId
    ) {
        commentService.updateComment(commentId, commentUpdateRequest.getContent());
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @AuthenticationPrincipal final LoginMemberRequest loginMemberRequest,
            @PathVariable final Long commentId
    ) {
        commentService.deleteComment(commentId, loginMemberRequest.getId());
        return ResponseEntity.noContent().build();
    }
}
