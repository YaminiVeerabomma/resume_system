package com.example.resume_system.service;

import com.example.resume_system.DTO.AuthResponse;
import com.example.resume_system.DTO.LoginRequest;
import com.example.resume_system.DTO.RegisterRequest;
import com.example.resume_system.config.JwtUtil;
import com.example.resume_system.entity.Resume;
import com.example.resume_system.entity.User;
import com.example.resume_system.repository.ResumeRepository;
import com.example.resume_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final ResumeRepository resumeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    // Register new user
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .contactNumber(request.getContactNumber())
                .gender(request.getGender())
                .githubURL(request.getGithubURL())
                .build();

        userRepository.save(user);

        // Auto-create empty Resume
        Resume resume = Resume.builder().user(user).build();
        resumeRepository.save(resume);

        String token = jwtUtil.generateToken(user.getEmail());

        return AuthResponse.builder()
                .token(token)
                .message("Registration successful")
                .build();
    }

    // Login user
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtUtil.generateToken(user.getEmail());

        return AuthResponse.builder()
                .token(token)
                .message("Login successful")
                .build();
    }
}
