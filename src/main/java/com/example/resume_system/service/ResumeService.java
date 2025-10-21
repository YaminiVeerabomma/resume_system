package com.example.resume_system.service;

import com.example.resume_system.DTO.*;
import com.example.resume_system.entity.*;
import com.example.resume_system.repository.ResumeRepository;
import com.example.resume_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;

@Service
@RequiredArgsConstructor
public class ResumeService {
    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;

    @Cacheable(value = "resumes", key = "#userId")
    public ResumeDTO getResume(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Resume resume = resumeRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Resume not found"));

        // map to DTO
        return ResumeDTO.builder()
                .id(resume.getId())
                .userId(user.getId())
                .tenthSchoolName(resume.getTenthSchoolName())
                .tenthPercentage(resume.getTenthPercentage())
                .interCollegeName(resume.getInterCollegeName())
                .interPercentage(resume.getInterPercentage())
                .degreeCollegeName(resume.getDegreeCollegeName())
                .degreePercentage(resume.getDegreePercentage())
                .skills(resume.getSkills().stream()
                        .map(skill -> SkillDTO.builder()
                                .id(skill.getId())
                                .skillName(skill.getSkillName())
                                .build())
                        .collect(Collectors.toList()))
                .internships(resume.getInternships().stream()
                        .map(i -> InternshipDTO.builder()
                                .id(i.getId())
                                .company(i.getCompany())
                                .duration(i.getDuration())
                                .projectName(i.getProjectName())
                                .description(i.getDescription())
                                .certificateURL(i.getCertificateURL())
                                .build())
                        .collect(Collectors.toList()))
                .courses(resume.getCourses().stream()
                        .map(c -> CourseDTO.builder()
                                .id(c.getId())
                                .coursePlatform(c.getCoursePlatform())
                                .CourseSkills(c.getCourseSkills())
                                .duration(c.getDuration())
                                .certificateURL(c.getCertificateURL())
                                .build())
                        .collect(Collectors.toList()))
                .projects(resume.getProjects().stream()
                        .map(p -> AcademicProjectDTO.builder()
                                .id(p.getId())
                                .projectName(p.getProjectName())
                                .description(p.getDescription())
                                .githubURL(p.getGithubURL())
                                .build())
                        .collect(Collectors.toList()))
                .achievements(resume.getAchievements().stream()
                        .map(a -> AchievementDTO.builder()
                                .id(a.getId())
                                .title(a.getTitle())
                                .description(a.getDescription())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    @CacheEvict(value = "resumes", key = "#userId")
    public ResumeDTO addOrUpdateEducation(Long userId, EducationDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Resume resume = resumeRepository.findByUser(user)
                .orElse(Resume.builder().user(user).build());

        resume.setTenthSchoolName(dto.getTenthSchoolName());
        resume.setTenthPercentage(dto.getTenthPercentage());
        resume.setInterCollegeName(dto.getInterCollegeName());
        resume.setInterPercentage(dto.getInterPercentage());
        resume.setDegreeCollegeName(dto.getDegreeCollegeName());
        resume.setDegreePercentage(dto.getDegreePercentage());

        resumeRepository.save(resume);

        return getResume(userId);
    }
}
