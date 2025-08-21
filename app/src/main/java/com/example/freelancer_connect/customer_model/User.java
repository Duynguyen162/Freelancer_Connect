package com.example.freelancer_connect.customer_model;

public class User {
    private String avatarUrl;
    private String birthday;
    private String cccd;
    private String email;
    private String gender;
    private boolean isOnline;
    private String name;
    private String phone;
    private boolean providerStatus;
    private String role;

    public User() {
    }

    public User(String avatarUrl, String birthday, String cccd, String email, String gender, boolean isOnline, String name, String phone, boolean providerStatus, String role) {
        this.avatarUrl = avatarUrl;
        this.birthday = birthday;
        this.cccd = cccd;
        this.email = email;
        this.gender = gender;
        this.isOnline = isOnline;
        this.name = name;
        this.phone = phone;
        this.providerStatus = providerStatus;
        this.role = role;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isProviderStatus() {
        return providerStatus;
    }

    public void setProviderStatus(boolean providerStatus) {
        this.providerStatus = providerStatus;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
