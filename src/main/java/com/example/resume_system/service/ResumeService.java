package com.example.resume_system.service;

import com.example.resume_system.DTO.ResumeDTO;
import com.example.resume_system.entity.User;
import com.example.resume_system.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.resume_system.exception.ResourceNotFoundException;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final UserRepository userRepository;
    private final InternshipRepository internshipRepository;
    private final CertificationRepository certificationRepository;

    // Fetch resume
    public ResumeDTO getResume(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        ResumeDTO resumeDTO = ResumeDTO.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .projects(user.getProjects())
                .skills(user.getSkills())
                .courses(user.getCourses())
                .achievements(user.getAchievements())
                .internships(
                        internshipRepository.findByUserId(userId)
                                .stream()
                                .map(i -> i.getRole() + " | " + i.getCompany() + " | " + i.getDuration())
                                .collect(Collectors.toList())
                )
                .certifications(
                        certificationRepository.findByUserId(userId)
                                .stream()
                                .map(c -> c.getName() + " – " + c.getPlatform() + " (" + c.getYear() + ")")
                                .collect(Collectors.toList())
                )
                .build();

        return resumeDTO;
    }

    // Update resume
    public ResumeDTO updateResume(Long userId, ResumeDTO resumeDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        // Update basic fields
        user.setProjects(resumeDTO.getProjects());
        user.setSkills(resumeDTO.getSkills());
        user.setCourses(resumeDTO.getCourses());
        user.setAchievements(resumeDTO.getAchievements());

        userRepository.save(user);

        // Update internships
        internshipRepository.deleteByUserId(userId);
        resumeDTO.getInternships().forEach(i -> {
            String[] parts = i.split("\\|");
            internshipRepository.save(new com.example.resume_system.entity.Internship(
                    userId,
                    parts[0].trim(),
                    parts[1].trim(),
                    parts[2].trim()
            ));
        });

        // Update certifications
        certificationRepository.deleteByUserId(userId);
        resumeDTO.getCertifications().forEach(c -> {
            String[] parts1 = c.split("–");
            String name = parts1[0].trim();
            String[] parts2 = parts1[1].split("\\(");
            String platform = parts2[0].trim();
            int year = Integer.parseInt(parts2[1].replace(")", "").trim());
            certificationRepository.save(new com.example.resume_system.entity.Certification(
                    userId,
                    name,
                    platform,
                    year
            ));
        });

        return getResume(userId);
    }

    // Delete resume
    public void deleteResume(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }

        internshipRepository.deleteByUserId(userId);
        certificationRepository.deleteByUserId(userId);
        userRepository.deleteById(userId);
    }
}
