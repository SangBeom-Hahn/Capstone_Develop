package com.kyonggi.Capstone_Develop.repository.noticeboard;

import com.kyonggi.Capstone_Develop.domain.noticeboard.NoticeBoard;
import com.kyonggi.Capstone_Develop.domain.noticeboard.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UploadFileRepository extends JpaRepository<UploadFile, Long> {
    List<UploadFile> findAllByNoticeBoard(NoticeBoard noticeBoard);
}
