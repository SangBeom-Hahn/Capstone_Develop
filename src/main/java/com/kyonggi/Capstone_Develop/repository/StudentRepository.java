package com.kyonggi.Capstone_Develop.repository;

import com.kyonggi.Capstone_Develop.domain.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
