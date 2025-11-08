package com.example.resume_system.controller;

import com.example.resume_system.DTO.*;
import com.example.resume_system.entity.*;
import com.example.resume_system.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resume")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Resume Management", description = "APIs for managing resume sections like education, skills, internships, projects, and achievements")
public class ResumeController {

    private final ResumeService resumeService;
    private final AcademicProjectService projectService;
    private final AchievementService achievementService;
    private final CourseService courseService;
    private final InternshipService internshipService;
    private final SkillService skillService;

    // ------------------ Resume ------------------
    @Operation(summary = "Get Resume", description = "Retrieve resume for a specific user")
    @GetMapping("/{userId}")
    public ResponseEntity<ResumeDTO> getResume(@PathVariable Long userId) {
        return ResponseEntity.ok(resumeService.getResume(userId));
    }

    @Operation(summary = "Add or Update Education", description = "Add or update the education section of the resume")
    @PostMapping("/{userId}/education")
    public ResponseEntity<ResumeDTO> addEducation(
            @PathVariable Long userId, @RequestBody EducationDTO dto) {
        return ResponseEntity.ok(resumeService.addOrUpdateEducation(userId, dto));
    }

    // ------------------ Academic Project ------------------
    @Operation(summary = "Add Academic Project", description = "Add an academic project to the resume")
    @PostMapping("/{userId}/projects")
    public ResponseEntity<AcademicProject> addProject(
            @PathVariable Long userId, @RequestBody AcademicProjectDTO dto) {
        return ResponseEntity.ok(projectService.addProject(userId, dto));
    }

    // ------------------ Achievement ------------------
    @Operation(summary = "Add Achievement", description = "Add an achievement to the resume")
    @PostMapping("/{userId}/achievements")
    public ResponseEntity<Achievement> addAchievement(
            @PathVariable Long userId, @RequestBody AchievementDTO dto) {
        return ResponseEntity.ok(achievementService.addAchievement(userId, dto));
    }

    // ------------------ Course ------------------
    @Operation(summary = "Add Course", description = "Add a course or certification to the resume")
    @PostMapping("/{userId}/courses")
    public ResponseEntity<Course> addCourse(
            @PathVariable Long userId, @RequestBody CourseDTO dto) {
        return ResponseEntity.ok(courseService.addCourse(userId, dto));
    }

    // ------------------ Internship ------------------
    @Operation(summary = "Add Internship", description = "Add an internship to the resume")
    @PostMapping("/{userId}/internships")
    public ResponseEntity<Internship> addInternship(
            @PathVariable Long userId, @RequestBody InternshipDTO dto) {
        return ResponseEntity.ok(internshipService.addInternship(userId, dto));
    }

    // ------------------ Skill ------------------
    @Operation(summary = "Add Skill", description = "Add a skill to the resume")
    @PostMapping("/{userId}/skills")
    public ResponseEntity<Skill> addSkill(
            @PathVariable Long userId, @RequestBody SkillDTO dto) {
        return ResponseEntity.ok(skillService.addSkill(userId, dto));
    }
}



//package com.example.resume_system.controller;
//
//import com.example.resume_system.DTO.*;
//import com.example.resume_system.entity.*;
//import com.example.resume_system.service.*;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/resume")
//@RequiredArgsConstructor
//@Tag(name = "Resume Management", description = "APIs for managing resumes, including skills, courses, projects, internships, and achievements")
//public class ResumeController {
//
//    private final ResumeService resumeService;
//    private final AcademicProjectService projectService;
//    private final AchievementService achievementService;
//    private final CourseService courseService;
//    private final InternshipService internshipService;
//    private final SkillService skillService;
//
//    // ------------------ Resume ------------------
//    @Operation(summary = "Get Resume", description = "Retrieve the resume for a specific user by their userId")
//    @GetMapping("/{userId}")
//    public ResponseEntity<ResumeDTO> getResume(@PathVariable Long userId) {
//        ResumeDTO resumeDTO = resumeService.getResume(userId);
//        return ResponseEntity.ok(resumeDTO);
//    }
//
//    @Operation(summary = "Add or Update Education", description = "Add or update the education section of a user's resume")
//    @PostMapping("/{userId}/education")
//    public ResponseEntity<ResumeDTO> addEducation(
//            @PathVariable Long userId,
//            @RequestBody EducationDTO dto) {
//        ResumeDTO resume = resumeService.addOrUpdateEducation(userId, dto);
//        return ResponseEntity.ok(resume);
//    }
//
//    // ------------------ Academic Project ------------------
//    @Operation(summary = "Add Academic Project", description = "Add an academic project to the user's resume")
//    @PostMapping("/{userId}/projects")
//    public ResponseEntity<AcademicProject> addProject(
//            @PathVariable Long userId,
//            @RequestBody AcademicProjectDTO dto) {
//        AcademicProject project = projectService.addProject(userId, dto);
//        return ResponseEntity.ok(project);
//    }
//
//    // ------------------ Achievement ------------------
//    @Operation(summary = "Add Achievement", description = "Add an achievement to the user's resume")
//    @PostMapping("/{userId}/achievements")
//    public ResponseEntity<Achievement> addAchievement(
//            @PathVariable Long userId,
//            @RequestBody AchievementDTO dto) {
//        Achievement achievement = achievementService.addAchievement(userId, dto);
//        return ResponseEntity.ok(achievement);
//    }
//
//    // ------------------ Course ------------------
//    @Operation(summary = "Add Course", description = "Add a course or certification to the user's resume")
//    @PostMapping("/{userId}/courses")
//    public ResponseEntity<Course> addCourse(
//            @PathVariable Long userId,
//            @RequestBody CourseDTO dto) {
//        Course course = courseService.addCourse(userId, dto);
//        return ResponseEntity.ok(course);
//    }
//
//    // ------------------ Internship ------------------
//    @Operation(summary = "Add Internship", description = "Add an internship to the user's resume")
//    @PostMapping("/{userId}/internships")
//    public ResponseEntity<Internship> addInternship(
//            @PathVariable Long userId,
//            @RequestBody InternshipDTO dto) {
//        Internship internship = internshipService.addInternship(userId, dto);
//        return ResponseEntity.ok(internship);
//    }
//
//    // ------------------ Skill ------------------
//    @Operation(summary = "Add Skill", description = "Add a skill to the user's resume")
//    @PostMapping("/{userId}/skills")
//    public ResponseEntity<Skill> addSkill(
//            @PathVariable Long userId,
//            @RequestBody SkillDTO dto) {
//        Skill skill = skillService.addSkill(userId, dto);
//        return ResponseEntity.ok(skill);
//    }
//}
