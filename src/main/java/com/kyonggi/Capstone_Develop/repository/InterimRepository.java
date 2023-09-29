package com.kyonggi.Capstone_Develop.repository;

import com.kyonggi.Capstone_Develop.domain.graduation.Apply;
import com.kyonggi.Capstone_Develop.domain.situation.Interim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InterimRepository extends JpaRepository<Interim, Long> {
    boolean existsByApply(Apply apply);
    
    Optional<Interim> findByApply(Apply apply);
}
