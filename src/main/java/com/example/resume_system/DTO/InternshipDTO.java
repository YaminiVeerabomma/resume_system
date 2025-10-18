package com.example.resume_system.DTO;


import com.example.resume_system.Enum.InternshipPlatform;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InternshipDTO {
    private Long id;
    private InternshipPlatform company;
    private String duration;
    private String projectName;
    private String description;
    private String certificateURL;
}
