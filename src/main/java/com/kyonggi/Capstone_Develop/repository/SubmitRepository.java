package com.kyonggi.Capstone_Develop.repository;

import com.kyonggi.Capstone_Develop.domain.graduation.Apply;
import com.kyonggi.Capstone_Develop.domain.situation.Submit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubmitRepository extends JpaRepository<Submit, Long> {
    boolean existsByApply(final Apply apply);
    
    Optional<Submit> findByApply(final Apply apply);
}
