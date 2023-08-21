package com.kyonggi.Capstone_Develop.repository;

import com.kyonggi.Capstone_Develop.domain.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByLoginId(String loginId);
    
    boolean existsByStudentNumber(String studentNumber);
    
    Optional<Student> findByLoginId(String loginId);
}
