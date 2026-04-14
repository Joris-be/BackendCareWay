package com.careway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendPasswordResetEmail(String toEmail, String resetToken, String userName, String userType) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("askrichayma7@gmail.com");
            message.setTo(toEmail);
            message.setSubject("CareWay - Réinitialiser votre mot de passe");

            String resetLink = "http://localhost:5188/reset-password?token=" + resetToken + "&userType=" + userType;

            String body = String.format(
                    "Bonjour %s,\n\n" +
                            "Vous avez demandé une réinitialisation de votre mot de passe CareWay.\n\n" +
                            "Votre token de réinitialisation :\n%s\n\n" +
                            "Ou cliquez sur ce lien :\n%s\n\n" +
                            "Ce token expire dans 1 heure.\n\n" +
                            "Si vous n'avez pas demandé cette réinitialisation, ignorez cet email.\n\n" +
                            "Cordialement,\nL'équipe CareWay",
                    userName,
                    resetToken,
                    resetLink);

            message.setText(body);
            mailSender.send(message);

            System.out.println("✉️ EMAIL SENT: Password reset email sent to " + toEmail);
        } catch (Exception e) {
            System.err.println("❌ EMAIL ERROR: Failed to send email to " + toEmail);
            e.printStackTrace();
        }
    }
}
