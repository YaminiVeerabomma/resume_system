package com.example.resume_system.entity;


import com.example.resume_system.Enum.Gender;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private String contactNumber;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String githubURL;

 

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Resume resume;
}
