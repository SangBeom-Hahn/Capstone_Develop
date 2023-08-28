package com.kyonggi.Capstone_Develop.repository;

import com.kyonggi.Capstone_Develop.domain.NoticeBoard;
import com.kyonggi.Capstone_Develop.domain.student.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class NoticeBoardRepositoryTest extends RepositoryTest{
    private Student sangbeom;
    
    private NoticeBoard noticeBoard;
    
    @BeforeEach
    void setUp() {
        sangbeom = new Student(
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
        
        noticeBoard = new NoticeBoard(
                "content",
                false,
                "title",
                1,
                sangbeom
        );
        sangbeom = studentRepository.save(sangbeom);
    }
    
    @Test
    @DisplayName("공지사항을 등록한다.")
    void save() {
        // when
        NoticeBoard saveNoticeBoard = noticeBoardRepository.save(noticeBoard);
        
        // then
        assertAll(
                () -> assertThat(saveNoticeBoard.getId()).isNotNull(),
                () -> assertThat(saveNoticeBoard).isEqualTo(noticeBoard)
        );
    }
    
    @Test
    @DisplayName("id로 공지사항을 찾는다.")
    void findById() {
        // given
        Long noticeBoardId = noticeBoardRepository.save(noticeBoard)
                .getId();
    
        // when
        NoticeBoard findNoticeBoard = noticeBoardRepository.findById(noticeBoardId)
                .orElseThrow();
    
        // then
        assertThat(findNoticeBoard)
                .extracting("id", "content", "fix", "title", "views")
                .containsExactly(noticeBoardId, "content", false, "title", 1);
    }
    
    @Test
    @DisplayName("공지사항 내용을 수정한다.")
    void updateContent() {
        // given
        Long noticeBoardId = noticeBoardRepository.save(noticeBoard)
                .getId();
        String changeContent = "changeContent";
    
        // when
        noticeBoard.changeContent(changeContent);
        NoticeBoard findNoticeBoard = noticeBoardRepository.findById(noticeBoardId)
                .orElseThrow();
    
        // then
        assertThat(findNoticeBoard.getContent())
                .isEqualTo(changeContent);
    }
    
    @Test
    @DisplayName("공지사항 제목을 수정한다.")
    void updateTitle() {
        // given
        Long noticeBoardId = noticeBoardRepository.save(noticeBoard)
                .getId();
        String changeTitle = "changeTitle";
        
        // when
        noticeBoard.changeTitle(changeTitle);
        NoticeBoard findNoticeBoard = noticeBoardRepository.findById(noticeBoardId)
                .orElseThrow();
        
        // then
        assertThat(findNoticeBoard.getTitle())
                .isEqualTo(changeTitle);
    }
    
    @Test
    @DisplayName("공지사항 고정 여부를 수정한다.")
    void updateFix() {
        // given
        Long noticeBoardId = noticeBoardRepository.save(noticeBoard)
                .getId();
        boolean changeFix = true;
        
        // when
        noticeBoard.changeFix(changeFix);
        NoticeBoard findNoticeBoard = noticeBoardRepository.findById(noticeBoardId)
                .orElseThrow();
        
        // then
        assertThat(findNoticeBoard.getFix())
                .isEqualTo(changeFix);
    }
    
    @Test
    @DisplayName("공지사항을 삭제한다.")
    void deleteNoticeBoard() {
        // given
        Long noticeBoardId = noticeBoardRepository.save(noticeBoard)
                .getId();
    
        // when
        noticeBoardRepository.deleteById(noticeBoardId);
      
        // then
        assertThat(noticeBoardRepository.findById(noticeBoardId))
                .isEmpty();
    }
}