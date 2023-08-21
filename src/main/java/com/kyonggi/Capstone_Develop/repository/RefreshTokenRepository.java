package com.kyonggi.Capstone_Develop.repository;

import com.kyonggi.Capstone_Develop.domain.refreshtoken.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByTokenValue(final String tokenValue);
    
    int deleteByTokenValue(final String tokenValue);
}
