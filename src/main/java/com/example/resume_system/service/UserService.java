package com.example.resume_system.service;

import com.example.resume_system.DTO.UserDTO;
import com.example.resume_system.entity.User;
import com.example.resume_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .contactNumber(user.getContactNumber())
                .gender(user.getGender())
                .githubURL(user.getGithubURL())
              
                .build();
    }
}
