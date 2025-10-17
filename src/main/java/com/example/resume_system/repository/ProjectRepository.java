package com.example.resume_system.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.resume_system.entity.Project;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByUserId(Long userId);
}
