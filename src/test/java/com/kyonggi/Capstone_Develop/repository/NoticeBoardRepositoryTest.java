package com.kyonggi.Capstone_Develop.repository;

import com.kyonggi.Capstone_Develop.config.JpaAuditingConfig;
import com.kyonggi.Capstone_Develop.domain.NoticeBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@Transactional
@Import(JpaAuditingConfig.class)
class NoticeBoardRepositoryTest {
    @Autowired
    NoticeBoardRepository noticeBoardRepository;
    
    private NoticeBoard noticeBoard;
    
    @BeforeEach
    void setUp() {
        noticeBoard = new NoticeBoard(
                "content",
                false,
                "title",
                1
        );
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
        assertThat(findNoticeBoard.isFix())
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