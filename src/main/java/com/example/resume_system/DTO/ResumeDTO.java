package com.example.resume_system.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.example.resume_system.entity.Achievement;
import com.example.resume_system.entity.Course;
import com.example.resume_system.entity.Project;
import com.example.resume_system.entity.Skill;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeDTO {
    private String username;
    private String email;
    private List<Project> projects;
    private List<Skill> skills;
    private List<Course> courses;
    private List<Achievement> achievements;
    private List<String> internships;
    private List<String> certifications;
}
