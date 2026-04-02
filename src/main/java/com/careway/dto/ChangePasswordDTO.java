package com.careway.dto;

import lombok.Data;

@Data
public class ChangePasswordDTO {
    private Integer patientId;
    private String currentPassword;
    private String newPassword;
    private String confirmPassword;
}
