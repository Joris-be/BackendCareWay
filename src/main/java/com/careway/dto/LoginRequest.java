package com.careway.dto;

public class LoginRequest {
    private String userType;
    private String code;
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(String userType, String code, String password) {
        this.userType = userType;
        this.code = code;
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
