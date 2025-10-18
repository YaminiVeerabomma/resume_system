package com.example.resume_system.repository;

import com.example.resume_system.entity.Course;
import com.example.resume_system.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByResume(Resume resume);
}
