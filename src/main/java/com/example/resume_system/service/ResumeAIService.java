package com.example.resume_system.service;

import com.example.resume_system.entity.Resume;
import com.example.resume_system.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

@Service
@RequiredArgsConstructor
public class ResumeAIService {

    private final ResumeRepository resumeRepository;

    /**
     * Generate AI-style professional resume summary
     */
    @Cacheable(value = "resumeSummary", key = "#userId")
    public String generateResumeSummary(Long userId) {
        Resume resume = resumeRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Resume not found"));

        StringBuilder summary = new StringBuilder();

        // ------------------ Education ------------------
        summary.append("Education: ");
        if(resume.getTenthSchoolName() != null) {
            summary.append("10th - ").append(resume.getTenthSchoolName())
                   .append(" (").append(resume.getTenthPercentage()).append("%), ");
        }
        if(resume.getInterCollegeName() != null) {
            summary.append("12th - ").append(resume.getInterCollegeName())
                   .append(" (").append(resume.getInterPercentage()).append("%), ");
        }
        if(resume.getDegreeCollegeName() != null) {
            summary.append("Degree - ").append(resume.getDegreeCollegeName())
                   .append(" (").append(resume.getDegreePercentage()).append("%)");
        }
        summary.append(".\n");

        // ------------------ Internships ------------------
        if(resume.getInternships() != null && !resume.getInternships().isEmpty()) {
            summary.append("Internships: ");
            resume.getInternships().forEach(i -> 
                summary.append(i.getCompany())
                       .append(" - ")
                       .append(i.getProjectName() != null ? i.getProjectName() : "")
                       .append(" (")
                       .append(i.getDuration() != null ? i.getDuration() : "")
                       .append("), ")
            );
            summary.setLength(summary.length()-2);
            summary.append(".\n");
        }

        // ------------------ Skills ------------------
        if(resume.getSkills() != null && !resume.getSkills().isEmpty()) {
            summary.append("Skills: ");
            resume.getSkills().forEach(s -> summary.append(s.getSkillName()).append(", "));
            summary.setLength(summary.length()-2);
            summary.append(".\n");
        }

        // ------------------ Courses ------------------
        if(resume.getCourses() != null && !resume.getCourses().isEmpty()) {
            summary.append("Courses: ");
            resume.getCourses().forEach(c -> 
                summary.append(c.getCoursePlatform())
                       .append(" - ")
                       .append(c.getCourseSkills() != null ? c.getCourseSkills() : "")
                       .append(" (")
                       .append(c.getDuration() != null ? c.getDuration() : "")
                       .append("), ")
            );
            summary.setLength(summary.length()-2);
            summary.append(".\n");
        }

        // ------------------ Academic Projects ------------------
        if(resume.getProjects() != null && !resume.getProjects().isEmpty()) {
            summary.append("Projects: ");
            resume.getProjects().forEach(p -> 
                summary.append(p.getProjectName()).append(" (")
                       .append(p.getDescription() != null ? p.getDescription() : "")
                       .append("), ")
            );
            summary.setLength(summary.length()-2);
            summary.append(".\n");
        }

        // ------------------ Achievements ------------------
        if(resume.getAchievements() != null && !resume.getAchievements().isEmpty()) {
            summary.append("Achievements: ");
            resume.getAchievements().forEach(a -> 
                summary.append(a.getTitle())
                       .append(a.getDescription() != null ? " - " + a.getDescription() : "")
                       .append(", ")
            );
            summary.setLength(summary.length()-2);
            summary.append(".\n");
        }

        return summary.toString();
    }
}
