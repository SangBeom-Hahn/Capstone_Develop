package com.kyonggi.Capstone_Develop.service;

import com.kyonggi.Capstone_Develop.controller.dto.PageInfo;
import com.kyonggi.Capstone_Develop.domain.file.RawFileData;
import com.kyonggi.Capstone_Develop.domain.noticeboard.NoticeBoard;
import com.kyonggi.Capstone_Develop.domain.noticeboard.UploadFile;
import com.kyonggi.Capstone_Develop.domain.student.Student;
import com.kyonggi.Capstone_Develop.exception.NoSuchFileIdException;
import com.kyonggi.Capstone_Develop.exception.NoSuchMemberException;
import com.kyonggi.Capstone_Develop.exception.NotAuthorException;
import com.kyonggi.Capstone_Develop.exception.NotFoundNoticeBoardException;
import com.kyonggi.Capstone_Develop.repository.StudentRepository;
import com.kyonggi.Capstone_Develop.repository.noticeboard.NoticeBoardRepository;
import com.kyonggi.Capstone_Develop.repository.noticeboard.UploadFileRepository;
import com.kyonggi.Capstone_Develop.service.dto.comment.CommentResponseDto;
import com.kyonggi.Capstone_Develop.service.dto.noticeboard.*;
import com.kyonggi.Capstone_Develop.support.UuidHolder;
import com.kyonggi.Capstone_Develop.support.file.FileConverter;
import com.kyonggi.Capstone_Develop.support.file.Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeBoardService {
    private final NoticeBoardRepository noticeBoardRepository;
    
    private final StudentRepository studentRepository;

    private final UploadFileRepository uploadFileRepository;

    private final Uploader uploader;

    private final UuidHolder uuidHolder;
    
    public NoticeBoardSaveResponseDto save(
            NoticeBoardSaveRequestDto noticeBoardSaveRequestDto,
            Long authorId
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
        return saveUploadFile(noticeBoardSaveRequestDto.getFiles(), saveNoticeBoard);
    }

    private NoticeBoardSaveResponseDto saveUploadFile(List<MultipartFile> files, NoticeBoard saveNoticeBoard) {
        List<String> uploadFileNames = new ArrayList<>();
        List<String> storeFileNames = new ArrayList<>();

        List<RawFileData> rawFileDatas = FileConverter.convertAssignmentFiles(files, uuidHolder);
        uploader.uploadFiles(rawFileDatas);

        rawFileDatas.forEach(rawFileData -> {
                    String uploadFileName = rawFileData.getUploadFileName();
                    String storeFileName = rawFileData.getStoreFileName();
                    uploadFileNames.add(uploadFileName);
                    storeFileNames.add(storeFileName);

                    UploadFile uploadFile = new UploadFile(
                            uploadFileName,
                            storeFileName,
                            saveNoticeBoard
                    );
                    uploadFileRepository.save(uploadFile);
                });

        return NoticeBoardSaveResponseDto.of(saveNoticeBoard, uploadFileNames, storeFileNames);
    }

    public NoticeBoardsResponseDto findAllNoticeBoard(Integer page, int count) {
        PageRequest pageRequest = PageRequest.of(page, count);
        Page<NoticeBoard> noticeBoards = noticeBoardRepository.findAllByOrderByIdDesc(pageRequest);
    
        List<AllNoticeBoardResponseDto> noticeBoardResponseDtos = noticeBoards.stream()
                .map(noticeBoard -> AllNoticeBoardResponseDto.from(noticeBoard))
                .collect(Collectors.toList());
    
        return NoticeBoardsResponseDto.of(noticeBoardResponseDtos, PageInfo.from(noticeBoards));
    }
    
    public NoticeBoardResponseDto findNoticeBoard(Long id) {
        NoticeBoard noticeBoard = noticeBoardRepository.findById(id)
                .orElseThrow(() -> new NotFoundNoticeBoardException(id));
        noticeBoard.view();
        
        List<CommentResponseDto> commentResponses = findCommentResponse(noticeBoard);
        return NoticeBoardResponseDto.of(noticeBoard, commentResponses);
    }
    
    public void updateNoticeBoard(NoticeBoardUpdateRequestDto noticeUpdateRequestDto, Long id) {
        NoticeBoard findNoticeBoard = noticeBoardRepository.findById(id)
                .orElseThrow(() -> new NotFoundNoticeBoardException(id));
    
        changeNoticeBoard(noticeUpdateRequestDto, findNoticeBoard);
    }
    
    private void changeNoticeBoard(NoticeBoardUpdateRequestDto noticeUpdateRequestDto, NoticeBoard findNoticeBoard) {
        findNoticeBoard.changeTitle(noticeUpdateRequestDto.getTitle());
        findNoticeBoard.changeContent(noticeUpdateRequestDto.getContent());
    }
    
    public void updateFix(Long id, Boolean fix) {
        NoticeBoard findNoticeBoard = noticeBoardRepository.findById(id)
                .orElseThrow(() -> new NotFoundNoticeBoardException(id));
        
        findNoticeBoard.changeFix(fix);
    }
    
    public void deleteNoticeBoard(Long noticeBoardId, Long authorId) {
        NoticeBoard findNoticeBoard = noticeBoardRepository.findById(noticeBoardId)
                .orElseThrow(() -> new NotFoundNoticeBoardException(noticeBoardId));
    
        validateAuthor(authorId, findNoticeBoard);
        noticeBoardRepository.deleteById(noticeBoardId);
    }
    
    private void validateAuthor(Long authorId, NoticeBoard noticeBoard) {
        if (!noticeBoard.isAuthor(authorId)) {
            throw new NotAuthorException(authorId);
        }
    }

    public NoticeBoardDownloadResponseDto downloadAttach(Long uploadFileId) {
        UploadFile findUploadFile = uploadFileRepository.findById(uploadFileId)
                .orElseThrow(() -> new NoSuchFileIdException(uploadFileId));

        return NoticeBoardDownloadResponseDto.from(findUploadFile);
    }
    
    private List<CommentResponseDto> findCommentResponse(NoticeBoard noticeBoard) {
        return noticeBoard.getComments()
                .stream()
                .map(comment -> CommentResponseDto.from(comment))
                .collect(Collectors.toList());
    }
}
