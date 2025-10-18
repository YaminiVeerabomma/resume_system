package com.example.resume_system.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EducationDTO {
    private String tenthSchoolName;
    private Float tenthPercentage;

    private String interCollegeName;
    private Float interPercentage;

    private String degreeCollegeName;
    private Float degreePercentage;
}
