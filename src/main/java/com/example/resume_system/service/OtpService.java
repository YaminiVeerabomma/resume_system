package com.example.resume_system.service;

import com.example.resume_system.entity.OtpToken;
import com.example.resume_system.repository.OtpTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OtpService {

    private final JavaMailSender mailSender;
    private final OtpTokenRepository otpTokenRepository;

    @Value("${app.otp.expiration-minutes:5}")
    private long otpExpiryMinutes;

    private final Random random = new Random();

    // ✅ Important: Add @Transactional here
    @Transactional
    public void sendOtp(String email) {

        // remove old expired entries
        otpTokenRepository.deleteByExpiryAtBefore(LocalDateTime.now());

        String otp = generateOtp();
        LocalDateTime expiry = LocalDateTime.now().plusMinutes(otpExpiryMinutes);

        OtpToken token = OtpToken.builder()
                .email(email)
                .otp(otp)
                .expiryAt(expiry)
                .used(false)
                .build();

        otpTokenRepository.save(token);

        // send mail
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your OTP Code");
        message.setText("Your OTP is: " + otp + "\nIt will expire in " + otpExpiryMinutes + " minutes.");
        mailSender.send(message);

        System.out.println("OTP for " + email + " is: " + otp);
    }

    public boolean verifyOtp(String email, String otp) {
        return otpTokenRepository.findTopByEmailAndUsedIsFalseOrderByExpiryAtDesc(email)
                .map(token -> {
                    if (token.getExpiryAt().isBefore(LocalDateTime.now())) {
                        return false;
                    }
                    if (!token.getOtp().equals(otp)) {
                        return false;
                    }
                    token.setUsed(true);
                    otpTokenRepository.save(token);
                    return true;
                })
                .orElse(false);
    }

    private String generateOtp() {
        int number = 100000 + random.nextInt(900000);
        return String.valueOf(number);
    }
}
