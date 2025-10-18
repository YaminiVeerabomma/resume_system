package com.example.resume_system.repository;

import com.example.resume_system.entity.AcademicProject;
import com.example.resume_system.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcademicProjectRepository extends JpaRepository<AcademicProject, Long> {

    List<AcademicProject> findByResume(Resume resume);
}
