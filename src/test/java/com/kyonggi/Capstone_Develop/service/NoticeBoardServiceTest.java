package com.kyonggi.Capstone_Develop.service;

import com.kyonggi.Capstone_Develop.controller.dto.noticeboard.NoticeBoardSaveRequest;
import com.kyonggi.Capstone_Develop.controller.dto.noticeboard.NoticeBoardUpdateRequest;
import com.kyonggi.Capstone_Develop.domain.Comment;
import com.kyonggi.Capstone_Develop.domain.NoticeBoard;
import com.kyonggi.Capstone_Develop.domain.student.*;
import com.kyonggi.Capstone_Develop.exception.NotAuthorException;
import com.kyonggi.Capstone_Develop.exception.NotFoundNoticeBoardException;
import com.kyonggi.Capstone_Develop.service.dto.comment.CommentResponseDto;
import com.kyonggi.Capstone_Develop.service.dto.noticeboard.AllNoticeBoardResponseDto;
import com.kyonggi.Capstone_Develop.service.dto.noticeboard.NoticeBoardResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NoticeBoardServiceTest extends ServiceTest{
    private Student student;
    
    private NoticeBoard noticeBoard1;
    
    private NoticeBoard noticeBoard2;
    
    private NoticeBoard noticeBoard3;
    
    private Comment comment1;
    
    private Comment comment2;
    
    @BeforeEach
    void setUp() {
        student = new Student(
                "201812709",
                "dummyPassword",
                LocalDate.of(2023, 07, 18),
                "컴퓨터공학부",
                PhoneNumber.from("010-1111-1111"),
                Sex.MALE,
                "한상범",
                Email.from("1@naver.com"),
                RoleType.STUDENT,
                "answerPW",
                Classification.from("UNDERGRADUATE_STUDENT")
        );
    
        noticeBoard1 = new NoticeBoard("content", false, "title", 0, student);
    
        noticeBoard2 = new NoticeBoard("content", false, "title", 0, student);
    
        noticeBoard3 = new NoticeBoard("content", false, "title", 0, student);
    
        comment1 = new Comment(noticeBoard1, student, "내용1");
    
        comment2 = new Comment(noticeBoard1, student, "내용2");
        
        studentRepository.save(student);
        noticeBoardRepository.save(noticeBoard1);
        noticeBoardRepository.save(noticeBoard2);
        noticeBoardRepository.save(noticeBoard3);
        commentRepository.save(comment1);
        commentRepository.save(comment2);
    }
    
    @Test
    @DisplayName("게시물 작성자가 아닌 사람이 게시물을 삭제하면 예외가 발생한다.")
    void throwException_invalidAuthor() {
        // given
        NoticeBoardSaveRequest noticeboardSaveRequest = createNoticeboardSaveRequest();
        Long saveNoticeBoardId = noticeBoardService.save(
                noticeboardSaveRequest.toServiceDto(),
                student.getId()
        ).getId();
        long invalidAuthorId = 999L;
        
        // then
        assertThatThrownBy(() -> noticeBoardService.deleteNoticeBoard(saveNoticeBoardId, invalidAuthorId))
                .isInstanceOf(NotAuthorException.class)
                .hasMessage(String.format("해당 회원은 작성자가 아닙니다. id={%d}", invalidAuthorId));
    }
    
    @Test
    @DisplayName("공지사항을 저장하고 id로 찾는다.")
    void saveNoticeBoardAndFind() {
        // given
        NoticeBoardSaveRequest noticeBoardSaveRequest = createNoticeboardSaveRequest();
        Long saveNoticeBoardId = noticeBoardService.save(
                noticeBoardSaveRequest.toServiceDto(),
                student.getId()
        ).getId();
    
        // when
        NoticeBoardResponseDto noticeBoardResponseDto =
                noticeBoardService.findNoticeBoard(saveNoticeBoardId);
    
        // then
        assertThat(noticeBoardResponseDto).extracting("id", "content", "fix", "title", "views", "authorLoginId")
                .containsExactly(saveNoticeBoardId, "content", false, "title", 1, "201812709");
    }
    
    @Test
    @DisplayName("모든 공지사항을 페이지 단위로 조회한다.")
    void findAll() {
        // given
        List<AllNoticeBoardResponseDto> actual = noticeBoardService.findAllNoticeBoard(0, 3)
                .getNoticeBoards();
    
        List<AllNoticeBoardResponseDto> expected = List.of(
                AllNoticeBoardResponseDto.from(noticeBoard3),
                AllNoticeBoardResponseDto.from(noticeBoard2),
                AllNoticeBoardResponseDto.from(noticeBoard1)
        );
      
        // then
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }
    
    @Test
    @DisplayName("게시물을 조회할 때 댓글도 함께 조회한다.")
    void findNoticeBoardWithComment() {
        // given
        List<CommentResponseDto> actual =
                noticeBoardService.findNoticeBoard(noticeBoard1.getId())
                        .getComments();
    
        List<CommentResponseDto> expected = List.of(
                CommentResponseDto.from(comment1),
                CommentResponseDto.from(comment2)
        );
    
        // then
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }
    
    @Test
    @DisplayName("공지사항을 조회하면 조회수가 1 증가한다.")
    void findThenUpdateView() {
        // given
        Integer expectedViews = noticeBoard1.getViews() + 1;
    
        // when
        NoticeBoardResponseDto noticeBoardResponseDto =
                noticeBoardService.findNoticeBoard(noticeBoard1.getId());
    
        // then
        assertThat(noticeBoardResponseDto.getViews())
                .isEqualTo(expectedViews);
    }
    
    @Test
    @DisplayName("저장한 공지사항의 제목과 내용을 수정한다.")
    void updateNoticeBoardContentAndTitle() {
        // given
        NoticeBoardSaveRequest noticeboardSaveRequest = createNoticeboardSaveRequest();
        Long saveNoticeBoardId = noticeBoardService.save(
                noticeboardSaveRequest.toServiceDto(),
                student.getId()
        ).getId();
        
        String expectedContent = "updateContent";
        String expectedTitle = "updateTitle";
        NoticeBoardUpdateRequest noticeBoardUpdateRequest =
                new NoticeBoardUpdateRequest(expectedContent, expectedTitle);
        
        // when
        noticeBoardService.updateNoticeBoard(
                noticeBoardUpdateRequest.toServiceDto(),
                saveNoticeBoardId
        );
    
        NoticeBoardResponseDto actual =
                noticeBoardService.findNoticeBoard(saveNoticeBoardId);
        
        // then
        assertThat(actual).extracting("id", "content", "title")
                .containsExactly(saveNoticeBoardId, expectedContent, expectedTitle);
    }
    
    @Test
    @DisplayName("저장한 공지사항의 고정 여부를 수정한다.")
    void updateFix() {
        // given
        NoticeBoardSaveRequest noticeboardSaveRequest = createNoticeboardSaveRequest();
        Long saveNoticeBoardId = noticeBoardService.save(
                noticeboardSaveRequest.toServiceDto(),
                student.getId()
        ).getId();
    
        Boolean expectedFix = true;
    
        // when
        noticeBoardService.updateFix(
                saveNoticeBoardId,
                expectedFix
        );
    
        NoticeBoardResponseDto actual = noticeBoardService.findNoticeBoard(saveNoticeBoardId);
    
        // then
        assertThat(actual).extracting("id", "fix")
                .containsExactly(saveNoticeBoardId, expectedFix);
    }
    
    @Test
    @DisplayName("공지사항을 id로 삭제한다.")
    void deleteNoticeBoard() {
        // given
        NoticeBoardSaveRequest noticeboardSaveRequest = createNoticeboardSaveRequest();
        Long saveNoticeBoardId = noticeBoardService.save(
                noticeboardSaveRequest.toServiceDto(),
                student.getId()
        ).getId();
        
        // when
        noticeBoardService.deleteNoticeBoard(saveNoticeBoardId, student.getId());
        
        // then
        assertThatThrownBy(() -> noticeBoardService.findNoticeBoard(saveNoticeBoardId))
                .isInstanceOf(NotFoundNoticeBoardException.class)
                .hasMessage(String.format("해당 공지사항이 존재하지 않습니다. id={%d}", saveNoticeBoardId));
    }
    
    private NoticeBoardSaveRequest createNoticeboardSaveRequest() {
        return new NoticeBoardSaveRequest("content", false, "title", 0);
    }
}