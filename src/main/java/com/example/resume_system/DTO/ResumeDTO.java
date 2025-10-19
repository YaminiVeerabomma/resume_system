package com.example.resume_system.DTO;

import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeDTO {
    private Long id;
    private Long userId;

    private String tenthSchoolName;
    private Float tenthPercentage;
    private String interCollegeName;
    private Float interPercentage;
    private String degreeCollegeName;
    private Float degreePercentage;

    @Builder.Default
    private List<InternshipDTO> internships = new ArrayList<>();

    @Builder.Default
    private List<CourseDTO> courses = new ArrayList<>();

    @Builder.Default
    private List<AcademicProjectDTO> projects = new ArrayList<>();

    @Builder.Default
    private List<SkillDTO> skills = new ArrayList<>();

    @Builder.Default
    private List<AchievementDTO> achievements = new ArrayList<>();
}
