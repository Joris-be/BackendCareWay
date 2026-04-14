package com.careway.controller;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.careway.dto.LoginRequest;
import com.careway.dto.LoginResponse;
import com.careway.entity.Medecin;
import com.careway.entity.Patient;
import com.careway.service.MedecinService;
import com.careway.service.PatientService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final MedecinService medecinService;
    private final PatientService patientService;

    // In-memory token store: token -> {userType, userId, expiryTime}
    private static final ConcurrentHashMap<String, ResetTokenData> resetTokens = new ConcurrentHashMap<>();
    private static final long TOKEN_EXPIRY_TIME = 3600000; // 1 hour in milliseconds

    public AuthController(MedecinService medecinService, PatientService patientService) {
        this.medecinService = medecinService;
        this.patientService = patientService;
    }

    // DTO for reset token data
    static class ResetTokenData {
        String userType;
        Integer userId;
        long expiryTime;

        ResetTokenData(String userType, Integer userId) {
            this.userType = userType;
            this.userId = userId;
            this.expiryTime = System.currentTimeMillis() + TOKEN_EXPIRY_TIME;
        }

        boolean isExpired() {
            return System.currentTimeMillis() > expiryTime;
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        try {
            if ("medecin".equalsIgnoreCase(request.getUserType())) {
                // Medecin login with RPPS code
                Medecin medecin = medecinService.getMedecinByRPPS(request.getCode())
                        .orElse(null);

                if (medecin == null) {
                    return ResponseEntity.ok(
                            new LoginResponse(false, "RPPS code non trouvé", "medecin", null, null));
                }

                if (!medecinService.validateMedecinPassword(medecin, request.getPassword())) {
                    return ResponseEntity.ok(
                            new LoginResponse(false, "Mot de passe incorrect", "medecin", null, null));
                }

                return ResponseEntity.ok(
                        new LoginResponse(
                                true,
                                "Connexion réussie",
                                "medecin",
                                medecin.getIdmedecin(),
                                medecin.getPrenom() + " " + medecin.getNom(),
                                medecin.getSpecialite(),
                                medecin.getMail()));

            } else if ("patient".equalsIgnoreCase(request.getUserType())) {
                // Patient login with NSS code
                Patient patient = patientService.getPatientByNSS(request.getCode());

                if (patient == null) {
                    return ResponseEntity.ok(
                            new LoginResponse(false, "NSS non trouvé", "patient", null, null));
                }

                if (!patientService.validatePatientPassword(patient, request.getPassword())) {
                    return ResponseEntity.ok(
                            new LoginResponse(false, "Mot de passe incorrect", "patient", null, null));
                }

                return ResponseEntity.ok(
                        new LoginResponse(
                                true,
                                "Connexion réussie",
                                "patient",
                                patient.getIdpatient(),
                                patient.getPrenom() + " " + patient.getNom()));

            } else {
                return ResponseEntity.ok(
                        new LoginResponse(false, "Type d'utilisateur invalide", null, null, null));
            }

        } catch (Exception e) {
            return ResponseEntity.ok(
                    new LoginResponse(false, "Erreur lors de la connexion: " + e.getMessage(), null, null, null));
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        try {
            Integer userId = null;
            String userType = request.getUserType();

            if ("medecin".equalsIgnoreCase(userType)) {
                Medecin medecin = medecinService.getMedecinByRPPS(request.getCode()).orElse(null);
                if (medecin == null || !medecin.getMail().equalsIgnoreCase(request.getEmail())) {
                    return ResponseEntity.ok(new ResetResponse(false, "Medecin not found or email mismatch"));
                }
                userId = medecin.getIdmedecin();
            } else if ("patient".equalsIgnoreCase(userType)) {
                Patient patient = patientService.getPatientByNSS(request.getCode());
                if (patient == null || !patient.getMail().equalsIgnoreCase(request.getEmail())) {
                    return ResponseEntity.ok(new ResetResponse(false, "Patient not found or email mismatch"));
                }
                userId = patient.getIdpatient();
            } else {
                return ResponseEntity.ok(new ResetResponse(false, "Invalid user type"));
            }

            // Generate reset token
            String resetToken = UUID.randomUUID().toString();
            resetTokens.put(resetToken, new ResetTokenData(userType, userId));

            // In production, send email with reset link containing the token
            // For now, just return the token
            System.out.println("PASSWORD RESET TOKEN: " + resetToken + " // User: " + userType + " ID: " + userId);

            return ResponseEntity.ok(new ResetResponse(true, "Reset token generated", resetToken));

        } catch (Exception e) {
            return ResponseEntity.ok(new ResetResponse(false, "Error: " + e.getMessage()));
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest request) {
        try {
            String resetToken = request.getResetToken();
            String newPassword = request.getNewPassword();

            // Check if token exists and is valid
            if (!resetTokens.containsKey(resetToken)) {
                return ResponseEntity.ok(new ResetResponse(false, "Invalid reset token"));
            }

            ResetTokenData tokenData = resetTokens.get(resetToken);
            if (tokenData.isExpired()) {
                resetTokens.remove(resetToken);
                return ResponseEntity.ok(new ResetResponse(false, "Reset token has expired"));
            }

            // Update password based on user type
            if ("medecin".equalsIgnoreCase(tokenData.userType)) {
                medecinService.updateMedecinPassword(tokenData.userId, newPassword);
            } else if ("patient".equalsIgnoreCase(tokenData.userType)) {
                patientService.updatePatientPassword(tokenData.userId, newPassword);
            }

            // Remove the used token
            resetTokens.remove(resetToken);

            return ResponseEntity.ok(new ResetResponse(true, "Password reset successfully"));

        } catch (Exception e) {
            return ResponseEntity.ok(new ResetResponse(false, "Error: " + e.getMessage()));
        }
    }

    // DTOs
    static class ForgotPasswordRequest {
        String userType;
        String code;
        String email;

        // Getters
        public String getUserType() {
            return userType;
        }

        public String getCode() {
            return code;
        }

        public String getEmail() {
            return email;
        }

        // Setters
        public void setUserType(String userType) {
            this.userType = userType;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    static class ResetPasswordRequest {
        String resetToken;
        String newPassword;

        // Getters
        public String getResetToken() {
            return resetToken;
        }

        public String getNewPassword() {
            return newPassword;
        }

        // Setters
        public void setResetToken(String resetToken) {
            this.resetToken = resetToken;
        }

        public void setNewPassword(String newPassword) {
            this.newPassword = newPassword;
        }
    }

    static class ResetResponse {
        boolean success;
        String message;
        String resetToken;

        ResetResponse(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        ResetResponse(boolean success, String message, String resetToken) {
            this.success = success;
            this.message = message;
            this.resetToken = resetToken;
        }

        // Getters
        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }

        public String getResetToken() {
            return resetToken;
        }
    }
}
