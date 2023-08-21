package com.kyonggi.Capstone_Develop.repository;

import com.kyonggi.Capstone_Develop.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
