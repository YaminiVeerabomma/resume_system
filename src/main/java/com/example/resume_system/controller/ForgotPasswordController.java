package com.example.resume_system.controller;

import com.example.resume_system.DTO.ForgotPasswordRequest;
import com.example.resume_system.DTO.ResetPasswordRequest;
import com.example.resume_system.service.ForgotPasswordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
@Tag(name = "Password Management", description = "APIs for forgot and reset password")
public class ForgotPasswordController {

    @Autowired
    private ForgotPasswordService forgotPasswordService;

    @Operation(summary = "Forgot Password", description = "Process forgot password request")
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        String response = forgotPasswordService.processForgotPassword(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Reset Password", description = "Reset password using the token or credentials")
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
        String response = forgotPasswordService.resetPassword(request);
        return ResponseEntity.ok(response);
    }
}
