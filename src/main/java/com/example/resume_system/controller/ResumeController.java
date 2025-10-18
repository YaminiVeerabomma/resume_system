package com.example.resume_system.controller;

import com.example.resume_system.DTO.*;
import com.example.resume_system.entity.*;
import com.example.resume_system.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resume")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;
    private final AcademicProjectService projectService;
    private final AchievementService achievementService;
    private final CourseService courseService;
    private final InternshipService internshipService;
    private final SkillService skillService;

    // ------------------ Resume ------------------
    @GetMapping("/{userId}")
    public ResponseEntity<ResumeDTO> getResume(@PathVariable Long userId) {
        ResumeDTO resumeDTO = resumeService.getResume(userId);
        return ResponseEntity.ok(resumeDTO);
    }

    @PostMapping("/{userId}/education")
    public ResponseEntity<ResumeDTO> addEducation(
            @PathVariable Long userId,
            @RequestBody EducationDTO dto) {
        ResumeDTO resume = resumeService.addOrUpdateEducation(userId, dto);
        return ResponseEntity.ok(resume);
    }

    
    // ------------------ Academic Project ------------------
    @PostMapping("/{userId}/projects")
    public ResponseEntity<AcademicProject> addProject(
            @PathVariable Long userId,
            @RequestBody AcademicProjectDTO dto) {
        AcademicProject project = projectService.addProject(userId, dto);
        return ResponseEntity.ok(project);
    }

    // ------------------ Achievement ------------------
    @PostMapping("/{userId}/achievements")
    public ResponseEntity<Achievement> addAchievement(
            @PathVariable Long userId,
            @RequestBody AchievementDTO dto) {
        Achievement achievement = achievementService.addAchievement(userId, dto);
        return ResponseEntity.ok(achievement);
    }

    // ------------------ Course ------------------
    @PostMapping("/{userId}/courses")
    public ResponseEntity<Course> addCourse(
            @PathVariable Long userId,
            @RequestBody CourseDTO dto) {
        Course course = courseService.addCourse(userId, dto);
        return ResponseEntity.ok(course);
    }

    // ------------------ Internship ------------------
    @PostMapping("/{userId}/internships")
    public ResponseEntity<Internship> addInternship(
            @PathVariable Long userId,
            @RequestBody InternshipDTO dto) {
        Internship internship = internshipService.addInternship(userId, dto);
        return ResponseEntity.ok(internship);
    }

    // ------------------ Skill ------------------
    @PostMapping("/{userId}/skills")
    public ResponseEntity<Skill> addSkill(
            @PathVariable Long userId,
            @RequestBody SkillDTO dto) {
        Skill skill = skillService.addSkill(userId, dto);
        return ResponseEntity.ok(skill);
    }
}
