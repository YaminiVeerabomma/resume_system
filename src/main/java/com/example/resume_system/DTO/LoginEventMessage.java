package com.example.resume_system.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginEventMessage {
    private Long userId;
    private String email;
    private String loginTime;
    
}
