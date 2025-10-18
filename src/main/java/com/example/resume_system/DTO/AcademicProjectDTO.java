package com.example.resume_system.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AcademicProjectDTO {
    private Long id;
    private String projectName;
    private String description;
    private String githubURL;
}
