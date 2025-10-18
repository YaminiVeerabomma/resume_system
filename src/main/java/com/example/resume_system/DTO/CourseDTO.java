package com.example.resume_system.DTO;


import com.example.resume_system.Enum.CoursePlatform;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDTO {
    private Long id;
    private CoursePlatform coursePlatform;
    private String CourseSkills;
    private String duration;
    private String certificateURL;
}
