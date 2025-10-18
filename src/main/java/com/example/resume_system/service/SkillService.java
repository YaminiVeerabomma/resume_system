package com.example.resume_system.service;

import com.example.resume_system.DTO.SkillDTO;
import com.example.resume_system.entity.Skill;
import com.example.resume_system.entity.Resume;
import com.example.resume_system.repository.SkillRepository;
import com.example.resume_system.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;
    private final ResumeRepository resumeRepository;

    public Skill addSkill(Long userId, SkillDTO dto) {
        Resume resume = resumeRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Resume not found"));

        Skill skill = Skill.builder()
                .skillName(dto.getSkillName())
                .resume(resume)
                .build();

        return skillRepository.save(skill);
    }
}
