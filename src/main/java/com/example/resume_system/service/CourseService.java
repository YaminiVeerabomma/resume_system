package com.example.resume_system.service;

import com.example.resume_system.DTO.CourseDTO;
import com.example.resume_system.entity.Course;
import com.example.resume_system.entity.Resume;
import com.example.resume_system.repository.CourseRepository;
import com.example.resume_system.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final ResumeRepository resumeRepository;

    public Course addCourse(Long userId, CourseDTO dto) {
        Resume resume = resumeRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Resume not found"));

        Course course = Course.builder()
                .coursePlatform(dto.getCoursePlatform()) 
                .courseSkills(dto.getCourseSkills())
                .duration(dto.getDuration())
                .certificateURL(dto.getCertificateURL())
                .resume(resume)
                .build();


        return courseRepository.save(course);
    }
}
