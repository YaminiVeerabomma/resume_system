package com.example.resume_system.repository;

import com.example.resume_system.entity.Achievement;
import com.example.resume_system.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, Long> {

    List<Achievement> findByResume(Resume resume);
}
