package com.example.resume_system.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.resume_system.entity.ResumeSummary;

@Repository
public interface ResumeSummaryRepository extends JpaRepository<ResumeSummary, Long> {
}
