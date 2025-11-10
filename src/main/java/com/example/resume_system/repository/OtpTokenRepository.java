package com.example.resume_system.repository;

import com.example.resume_system.entity.OtpToken;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpTokenRepository extends JpaRepository<OtpToken, Long> {

    Optional<OtpToken> findTopByEmailAndUsedIsFalseOrderByExpiryAtDesc(String email);
    Optional<OtpToken> findTopByPhoneAndUsedIsFalseOrderByExpiryAtDesc(String phone);
	 @Modifying
	    @Transactional
    void deleteByExpiryAtBefore(java.time.LocalDateTime time);
}
