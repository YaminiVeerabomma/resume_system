package com.example.resume_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.resume_system.entity.Internship;

public interface InternshipRepository extends JpaRepository<Internship, Long> {
    List<Internship> findByUserId(Long userId);
}