package com.kyonggi.Capstone_Develop.repository;

import com.kyonggi.Capstone_Develop.domain.refreshtoken.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.time.LocalDateTime;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByTokenValue(final String tokenValue);
    
    int deleteByTokenValue(final String tokenValue);

    @Modifying(clearAutomatically = true)
    long deleteByExpiredTimeBefore(LocalDateTime deleteTime);
}
