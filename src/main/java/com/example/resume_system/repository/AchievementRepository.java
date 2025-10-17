package com.example.resume_system.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.resume_system.entity.Achievement;

import java.util.List;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {
    List<Achievement> findByUserId(Long userId);
}
