package com.example.resume_system.DTO;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeDTO {

    private Long id;
    private Long userId;

    // Education details
    private String tenthSchoolName;
    private Float tenthPercentage;

    private String interCollegeName;
    private Float interPercentage;

    private String degreeCollegeName;
    private Float degreePercentage;

    // Related Data
    private List<InternshipDTO> internships;
    private List<CourseDTO> courses;
    private List<AcademicProjectDTO> projects;
    private List<SkillDTO> skills;
    private List<AchievementDTO> achievements;
}
