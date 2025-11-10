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


    // ✅ Detect whether input is email or phone
    private boolean isEmail(String input) {
        return input.contains("@");
    }


    // ✅ SEND OTP (email or phone)
    @Transactional
    public void sendOtp(String emailOrPhone) {

        otpTokenRepository.deleteByExpiryAtBefore(LocalDateTime.now());

        String otp = generateOtp();
        LocalDateTime expiry = LocalDateTime.now().plusMinutes(otpExpiryMinutes);

        OtpToken token;

        if (isEmail(emailOrPhone)) {

            token = OtpToken.builder()
                    .email(emailOrPhone)
                    .otp(otp)
                    .expiryAt(expiry)
                    .used(false)
                    .build();

            otpTokenRepository.save(token);

            // ✅ SEND EMAIL
            sendOtpEmail(emailOrPhone, otp);

        } else {

            token = OtpToken.builder()
                    .phone(emailOrPhone)
                    .otp(otp)
                    .expiryAt(expiry)
                    .used(false)
                    .build();

            otpTokenRepository.save(token);

            // ✅ SEND SMS (just printing)
            System.out.println("📱 OTP to " + emailOrPhone + " = " + otp);
        }

        System.out.println("✅ Generated OTP: " + otp);
    }


    // ✅ Email OTP sending
    private void sendOtpEmail(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your OTP Code");
        message.setText("Your OTP is: " + otp + "\nExpires in " + otpExpiryMinutes + " minutes.");
        mailSender.send(message);
    }


    // ✅ VERIFY OTP (email or phone)
    public boolean verifyOtp(String emailOrPhone, String otp) {

        if (isEmail(emailOrPhone)) {
            return otpTokenRepository
                    .findTopByEmailAndUsedIsFalseOrderByExpiryAtDesc(emailOrPhone)
                    .map(token -> validateOtp(token, otp))
                    .orElse(false);
        } else {
            return otpTokenRepository
                    .findTopByPhoneAndUsedIsFalseOrderByExpiryAtDesc(emailOrPhone)
                    .map(token -> validateOtp(token, otp))
                    .orElse(false);
        }
    }


    // ✅ Validate OTP logic
    private boolean validateOtp(OtpToken token, String otp) {

        if (token.getExpiryAt().isBefore(LocalDateTime.now())) {
            return false; // expired
        }

        if (!token.getOtp().equals(otp)) {
            return false; // incorrect
        }

        token.setUsed(true);
        otpTokenRepository.save(token);
        return true;
    }


    // ✅ 6-digit OTP generator
    private String generateOtp() {
        int number = 100000 + random.nextInt(900000);
        return String.valueOf(number);
    }
}
