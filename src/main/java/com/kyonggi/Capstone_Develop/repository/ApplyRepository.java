package com.kyonggi.Capstone_Develop.repository;

import com.kyonggi.Capstone_Develop.domain.graduation.Apply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplyRepository extends JpaRepository<Apply, Long> {
    List<Apply> findAllByStudentId(Long studentId);
}
