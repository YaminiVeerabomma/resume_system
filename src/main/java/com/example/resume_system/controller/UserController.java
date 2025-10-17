package com.example.resume_system.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.resume_system.DTO.AuthResponse;
import com.example.resume_system.DTO.LoginRequest;
import com.example.resume_system.DTO.RegisterRequest;
import com.example.resume_system.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        userService.register(request);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        String token = userService.login(request);
        return ResponseEntity.ok(new AuthResponse(token, "Login successful"));
    }
}

//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import com.example.resume_system.config.JwtUtil;
//import com.example.resume_system.entity.User;
//import com.example.resume_system.service.UserService;
//
//@RestController
//@RequestMapping("/api/auth")
//@RequiredArgsConstructor
//public class UserController {
//    private final UserService userService;
//    private final JwtUtil jwtUtil;
//
//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody User user) {
//        User saved = userService.register(user);
//        String token = jwtUtil.generateToken(saved.getEmail());
//        return ResponseEntity.ok(token);
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody User user) {
//        User existing = userService.getUserByEmail(user.getEmail());
//        if (existing != null && user.getPassword().equals(existing.getPassword())) {
//            String token = jwtUtil.generateToken(existing.getEmail());
//            return ResponseEntity.ok(token);
//        }
//        return ResponseEntity.status(401).body("Invalid credentials");
//    }
//}
