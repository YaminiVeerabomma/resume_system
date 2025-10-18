package com.example.resume_system.repository;

import com.example.resume_system.entity.Skill;
import com.example.resume_system.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

    List<Skill> findByResume(Resume resume);
}
