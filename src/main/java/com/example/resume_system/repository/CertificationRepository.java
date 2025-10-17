package com.example.resume_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.resume_system.entity.Certification;

public interface CertificationRepository extends JpaRepository<Certification, Long> {
    List<Certification> findByUserId(Long userId);
}