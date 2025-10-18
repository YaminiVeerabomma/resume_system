package com.example.resume_system.service;

import com.example.resume_system.DTO.AcademicProjectDTO;
import com.example.resume_system.entity.AcademicProject;
import com.example.resume_system.entity.Resume;
import com.example.resume_system.repository.AcademicProjectRepository;
import com.example.resume_system.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AcademicProjectService {

    private final AcademicProjectRepository academicProjectRepository;
    private final ResumeRepository resumeRepository;

    public AcademicProject addProject(Long userId, AcademicProjectDTO dto) {
        Resume resume = resumeRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Resume not found"));

        AcademicProject project = AcademicProject.builder()
                .projectName(dto.getProjectName())
                .description(dto.getDescription())
                .githubURL(dto.getGithubURL())
                .resume(resume)
                .build();

        return academicProjectRepository.save(project);
    }
}
