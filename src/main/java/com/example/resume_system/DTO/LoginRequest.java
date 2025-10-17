package com.example.resume_system.DTO;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}