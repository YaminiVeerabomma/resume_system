package com.example.resume_system.service;

import com.example.resume_system.DTO.ForgotPasswordRequest;
import com.example.resume_system.DTO.ResetPasswordRequest;
import com.example.resume_system.entity.PasswordResetToken;
import com.example.resume_system.entity.User;
import com.example.resume_system.repository.PasswordResetTokenRepository;
import com.example.resume_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class ForgotPasswordService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String processForgotPassword(ForgotPasswordRequest request) {
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
        if (userOpt.isEmpty()) {
            return "❌ User not found!";
        }

        User user = userOpt.get();

        // Delete old token if exists
        tokenRepository.deleteByUser(user);

        // Generate new token
        String token = UUID.randomUUID().toString();

        PasswordResetToken resetToken = PasswordResetToken.builder()
                .token(token)
                .user(user)
                .expiryDate(LocalDateTime.now().plusMinutes(10))
                .build();

        tokenRepository.save(resetToken);

        // For now, just return the token (you can log it or show it to frontend)
        return "✅ Password reset token: " + token;
    }

    public String resetPassword(ResetPasswordRequest request) {
        Optional<PasswordResetToken> tokenOpt = tokenRepository.findByToken(request.getToken());
        if (tokenOpt.isEmpty()) {
            return "❌ Invalid or expired token!";
        }

        PasswordResetToken resetToken = tokenOpt.get();

        if (resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            return "⏰ Token expired!";
        }

        User user = resetToken.getUser();
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        // Delete token after use
        tokenRepository.delete(resetToken);

        return "✅ Password updated successfully!";
    }
}
