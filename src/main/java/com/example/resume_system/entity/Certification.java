package com.example.resume_system.entity;



import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Certification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String platform; 
    private String year;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

