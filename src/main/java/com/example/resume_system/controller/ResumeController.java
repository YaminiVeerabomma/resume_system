package com.example.resume_system.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.resume_system.DTO.ResumeDTO;
import com.example.resume_system.service.ResumeService;

@RestController
@RequestMapping("/api/resume")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

   
    @GetMapping("/{userId}")
    public ResponseEntity<ResumeDTO> getResume(@PathVariable Long userId) {
        ResumeDTO resumeDTO = resumeService.getResume(userId);
        return ResponseEntity.ok(resumeDTO);
    }

   
    @PutMapping("/{userId}")
    public ResponseEntity<ResumeDTO> updateResume(
            @PathVariable Long userId,
            @RequestBody ResumeDTO resumeDTO) {

        ResumeDTO updated = resumeService.updateResume(userId, resumeDTO);
        return ResponseEntity.ok(updated);
    }

   
    @PostMapping("/{userId}/project")
    public ResponseEntity<ResumeDTO> addProject(
            @PathVariable Long userId,
            @RequestBody ResumeDTO resumeDTO) {

        ResumeDTO updated = resumeService.updateResume(userId, resumeDTO);
        return ResponseEntity.ok(updated);
    }

 
}


