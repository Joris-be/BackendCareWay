package com.careway.dto;

public class LoginResponse {
    private boolean success;
    private String message;
    private String userType;
    private Integer userId;
    private String userName;
    private String specialite;
    private String mail;

    public LoginResponse() {
    }

    public LoginResponse(boolean success, String message, String userType, Integer userId, String userName) {
        this.success = success;
        this.message = message;
        this.userType = userType;
        this.userId = userId;
        this.userName = userName;
    }

    public LoginResponse(boolean success, String message, String userType, Integer userId, String userName,
            String specialite, String mail) {
        this.success = success;
        this.message = message;
        this.userType = userType;
        this.userId = userId;
        this.userName = userName;
        this.specialite = specialite;
        this.mail = mail;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
