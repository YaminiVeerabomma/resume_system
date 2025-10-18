package com.example.resume_system.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    // Education details
    private String tenthSchoolName;
    private Float tenthPercentage;

    private String interCollegeName;
    private Float interPercentage;

    private String degreeCollegeName;
    private Float degreePercentage;

    // Relationships
    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Internship> internships;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Course> courses;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<AcademicProject> projects;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Skill> skills;
    

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Achievement> achievements;
}
