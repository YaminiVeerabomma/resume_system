package com.example.resume_system.service;

import com.example.resume_system.DTO.AchievementDTO;
import com.example.resume_system.entity.Achievement;
import com.example.resume_system.entity.Resume;
import com.example.resume_system.repository.AchievementRepository;
import com.example.resume_system.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AchievementService {

    private final AchievementRepository achievementRepository;
    private final ResumeRepository resumeRepository;

    public Achievement addAchievement(Long userId, AchievementDTO dto) {
        Resume resume = resumeRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Resume not found"));

        Achievement achievement = Achievement.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .resume(resume)
                .build();

        return achievementRepository.save(achievement);
    }
}
