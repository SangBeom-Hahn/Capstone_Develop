package com.kyonggi.Capstone_Develop.repository;

import com.kyonggi.Capstone_Develop.domain.graduation.Apply;
import com.kyonggi.Capstone_Develop.domain.situation.Final;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FinalRepository extends JpaRepository<Final, Long> {
    boolean existsByApply(Apply apply);
    
    Optional<Final> findByApply(Apply apply);
}
