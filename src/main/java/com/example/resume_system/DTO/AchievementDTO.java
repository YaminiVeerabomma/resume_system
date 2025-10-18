package com.example.resume_system.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AchievementDTO {
    private Long id;
    private String title;
    private String description;
}
