package com.kyonggi.Capstone_Develop.service;

import com.kyonggi.Capstone_Develop.controller.dto.PageInfo;
import com.kyonggi.Capstone_Develop.domain.NoticeBoard;
import com.kyonggi.Capstone_Develop.domain.student.Student;
import com.kyonggi.Capstone_Develop.exception.NoSuchMemberException;
import com.kyonggi.Capstone_Develop.exception.NotAuthorException;
import com.kyonggi.Capstone_Develop.exception.NotFoundNoticeBoardException;
import com.kyonggi.Capstone_Develop.repository.NoticeBoardRepository;
import com.kyonggi.Capstone_Develop.repository.StudentRepository;
import com.kyonggi.Capstone_Develop.service.dto.comment.CommentResponseDto;
import com.kyonggi.Capstone_Develop.service.dto.noticeboard.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeBoardService {
    private final NoticeBoardRepository noticeBoardRepository;
    
    private final StudentRepository studentRepository;
    
    public NoticeBoardSaveResponseDto save(
            final NoticeBoardSaveRequestDto noticeBoardSaveRequestDto,
            final Long authorId
    ) {
        Student author = studentRepository.findById(authorId)
                .orElseThrow(() -> new NoSuchMemberException(authorId));
    
        NoticeBoard noticeBoard = new NoticeBoard(
                noticeBoardSaveRequestDto.getContent(),
                noticeBoardSaveRequestDto.isFix(),
                noticeBoardSaveRequestDto.getTitle(),
                noticeBoardSaveRequestDto.getViews(),
                author
        );
        NoticeBoard saveNoticeBoard = noticeBoardRepository.save(noticeBoard);
        return NoticeBoardSaveResponseDto.from(saveNoticeBoard);
    }
    
    public NoticeBoardsResponseDto findAllNoticeBoard(final Integer page, final int count) {
        PageRequest pageRequest = PageRequest.of(page, count);
        Page<NoticeBoard> noticeBoards = noticeBoardRepository.findAllByOrderByIdDesc(pageRequest);
    
        List<AllNoticeBoardResponseDto> noticeBoardResponseDtos = noticeBoards.stream()
                .map(noticeBoard -> AllNoticeBoardResponseDto.from(noticeBoard))
                .collect(Collectors.toList());
    
        return NoticeBoardsResponseDto.of(noticeBoardResponseDtos, PageInfo.from(noticeBoards));
    }
    
    public NoticeBoardResponseDto findNoticeBoard(final Long id) {
        NoticeBoard noticeBoard = noticeBoardRepository.findById(id)
                .orElseThrow(() -> new NotFoundNoticeBoardException(id));
        noticeBoard.view();
        
        List<CommentResponseDto> commentResponses = findCommentResponse(noticeBoard);
        return NoticeBoardResponseDto.of(noticeBoard, commentResponses);
    }
    
    public void updateNoticeBoard(final NoticeBoardUpdateRequestDto noticeUpdateRequestDto, final Long id) {
        NoticeBoard findNoticeBoard = noticeBoardRepository.findById(id)
                .orElseThrow(() -> new NotFoundNoticeBoardException(id));
    
        changeNoticeBoard(noticeUpdateRequestDto, findNoticeBoard);
    }
    
    private void changeNoticeBoard(final NoticeBoardUpdateRequestDto noticeUpdateRequestDto, final NoticeBoard findNoticeBoard) {
        findNoticeBoard.changeTitle(noticeUpdateRequestDto.getTitle());
        findNoticeBoard.changeContent(noticeUpdateRequestDto.getContent());
    }
    
    public void updateFix(final Long id, final Boolean fix) {
        NoticeBoard findNoticeBoard = noticeBoardRepository.findById(id)
                .orElseThrow(() -> new NotFoundNoticeBoardException(id));
        
        findNoticeBoard.changeFix(fix);
    }
    
    public void deleteNoticeBoard(final Long noticeBoardId, final Long authorId) {
        NoticeBoard findNoticeBoard = noticeBoardRepository.findById(noticeBoardId)
                .orElseThrow(() -> new NotFoundNoticeBoardException(noticeBoardId));
    
        validateAuthor(authorId, findNoticeBoard);
        noticeBoardRepository.deleteById(noticeBoardId);
    }
    
    private void validateAuthor(final Long authorId, final NoticeBoard noticeBoard) {
        if (!noticeBoard.isAuthor(authorId)) {
            throw new NotAuthorException(authorId);
        }
    }
    
    private List<CommentResponseDto> findCommentResponse(final NoticeBoard noticeBoard) {
        return noticeBoard.getComments()
                .stream()
                .map(comment -> CommentResponseDto.from(comment))
                .collect(Collectors.toList());
    }
}
