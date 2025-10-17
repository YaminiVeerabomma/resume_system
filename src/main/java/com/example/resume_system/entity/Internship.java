package com.example.resume_system.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Internship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String company;
    private String role;
    private String duration; // e.g., "May–July 2025"

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
