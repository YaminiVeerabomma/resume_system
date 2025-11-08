package com.example.resume_system.controller;

import com.example.resume_system.service.ResumeAIService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resume")
@RequiredArgsConstructor
@Tag(name = "Resume AI", description = "Endpoints for generating AI-powered resume summaries")
public class ResumeAIController {

    private final ResumeAIService resumeAIService;

    @Operation(summary = "Generate professional resume summary", 
               description = "Automatically generates a resume summary for the given user based on their profile data.")
    @GetMapping("/{userId}/summary")
    public ResponseEntity<String> getResumeSummary(@PathVariable Long userId) {
        String summary = resumeAIService.generateResumeSummary(userId);
        return ResponseEntity.ok(summary);
    }
}