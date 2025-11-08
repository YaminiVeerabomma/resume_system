package com.example.resume_system.service;

import com.example.resume_system.DTO.AuthResponse;
import com.example.resume_system.DTO.LoginEventMessage;
import com.example.resume_system.DTO.LoginRequest;
import com.example.resume_system.DTO.RegisterRequest;
import com.example.resume_system.DTO.UserRegisterMessage;
import com.example.resume_system.config.JwtUtil;
import com.example.resume_system.entity.Resume;
import com.example.resume_system.entity.User;
import com.example.resume_system.producer.LoginEventProducer;
import com.example.resume_system.producer.UserRegisterProducer;
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
    private final UserRegisterProducer userRegisterProducer;
    private final LoginEventProducer loginEventProducer;

    // ✅ Register User
    public AuthResponse register(RegisterRequest request) {

        // Check email exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        // Save User
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .contactNumber(request.getContactNumber())
                .gender(request.getGender())
                .githubURL(request.getGithubURL())
                .build();

        userRepository.save(user);

        // Save Resume
        Resume resume = Resume.builder()
                .user(user)
                .build();

        resumeRepository.save(resume);

        // ✅ Send RabbitMQ event in JSON
        userRegisterProducer.sendRegisterEvent(
                new UserRegisterMessage(
                        user.getId(),
                        user.getEmail(),
                        user.getName()
                )
        );

        // Generate JWT Token
        String token = jwtUtil.generateToken(user.getEmail());

        return AuthResponse.builder()
                .token(token)
                .message("Registration successful")
                .build();
    }

    // ✅ Login User
    public AuthResponse login(LoginRequest request) {

        // Authenticate user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Fetch user details
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

     // ✅ Send Login Event to RabbitMQ
        loginEventProducer.sendLoginEvent(
                new LoginEventMessage(
                        user.getId(),
                        user.getEmail(),
                        "LOGIN"
                )
        );
        // Generate Token
        String token = jwtUtil.generateToken(user.getEmail());

        return AuthResponse.builder()
                .token(token)
                .message("Login successful")
                .build();
    }
}
