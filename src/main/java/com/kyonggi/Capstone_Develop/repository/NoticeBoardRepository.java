package com.kyonggi.Capstone_Develop.repository;

import com.kyonggi.Capstone_Develop.domain.NoticeBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeBoardRepository extends JpaRepository<NoticeBoard, Long> {
    Page<NoticeBoard> findAllByOrderByIdDesc(Pageable pageable);
    
    boolean existsById(Long id);
}
