package com.kyonggi.Capstone_Develop.controller;

import com.kyonggi.Capstone_Develop.controller.auth.AuthenticationPrincipal;
import com.kyonggi.Capstone_Develop.controller.dto.auth.CommentUpdateRequest;
import com.kyonggi.Capstone_Develop.controller.dto.auth.LoginMemberRequest;
import com.kyonggi.Capstone_Develop.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    
    @PutMapping("/{commentId}")
    public ResponseEntity<Void> updateComment(
            @AuthenticationPrincipal LoginMemberRequest loginMemberRequest,
            @RequestBody @Valid CommentUpdateRequest commentUpdateRequest,
            @PathVariable Long commentId
    ) {
        commentService.updateComment(commentId, commentUpdateRequest.getContent());
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @AuthenticationPrincipal LoginMemberRequest loginMemberRequest,
            @PathVariable Long commentId
    ) {
        commentService.deleteComment(commentId, loginMemberRequest.getId());
        return ResponseEntity.noContent().build();
    }
}
