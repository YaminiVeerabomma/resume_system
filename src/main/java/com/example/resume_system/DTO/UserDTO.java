package com.example.resume_system.DTO;

import com.example.resume_system.Enum.Gender;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String password; // optional – include only for registration/login requests
    private String contactNumber;
    private Gender gender;
  

    // Optional Links
    private String githubURL;
    private String linkedinURL;
    private String portfolioURL;
}
