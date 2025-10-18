package com.example.resume_system.service;

import com.example.resume_system.DTO.InternshipDTO;
import com.example.resume_system.entity.Internship;
import com.example.resume_system.entity.Resume;
import com.example.resume_system.repository.InternshipRepository;
import com.example.resume_system.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InternshipService {

    private final InternshipRepository internshipRepository;
    private final ResumeRepository resumeRepository;

    public Internship addInternship(Long userId, InternshipDTO dto) {
        Resume resume = resumeRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Resume not found"));

        Internship internship = Internship.builder()
                .company(dto.getCompany())
                .duration(dto.getDuration())
                .projectName(dto.getProjectName())
                .description(dto.getDescription())
                .certificateURL(dto.getCertificateURL())
                .resume(resume)
                .build();

        return internshipRepository.save(internship);
    }
}
