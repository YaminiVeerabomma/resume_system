package com.example.resume_system.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.resume_system.entity.Course;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByUserId(Long userId);
}
