package com.example.resume_system.DTO;



import lombok.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterMessage implements Serializable {
    private Long userId;
    private String email;
    private String name;
}
