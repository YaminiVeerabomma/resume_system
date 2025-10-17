package com.example.resume_system.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String institution; 
    private String year;        

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
