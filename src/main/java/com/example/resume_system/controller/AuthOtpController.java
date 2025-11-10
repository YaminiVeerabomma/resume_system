package com.example.resume_system.controller;

import com.example.resume_system.service.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthOtpController {

    private final OtpService otpService;

    @PostMapping("/send-otp")
    public ResponseEntity<?> sendOtp(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        if (email == null || email.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "email is required"));
        }
        otpService.sendOtp(email.trim());
        return ResponseEntity.ok(Map.of("message", "OTP sent if email exists. Check inbox/spam."));
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String otp = body.get("otp");
        if (email == null || otp == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "email and otp are required"));
        }
        boolean ok = otpService.verifyOtp(email.trim(), otp.trim());
        if (ok) {
            // On success, you may generate a JWT or mark user as verified
            return ResponseEntity.ok(Map.of("message", "OTP verified"));
        } else {
            return ResponseEntity.status(400).body(Map.of("message", "Invalid or expired OTP"));
        }
    }
}
