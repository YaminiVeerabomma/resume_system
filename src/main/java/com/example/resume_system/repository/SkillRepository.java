package com.example.resume_system.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.resume_system.entity.Skill;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    List<Skill> findByUserId(Long userId);
}

