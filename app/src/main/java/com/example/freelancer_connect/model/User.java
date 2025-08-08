package com.example.freelancer_connect.model;

public class User {
    private String name;
    private String code;
    private String email;
    private String role;
    private String phone;
    private String address;
    private int avatarResId;

    public User(String name, String code, String email, String role, String phone, String address, int avatarResId) {
        this.name = name;
        this.code = code;
        this.email = email;
        this.role = role;
        this.phone = phone;
        this.address = address;
        this.avatarResId = avatarResId;
    }

    public String getName() { return name; }
    public String getCode() { return code; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public int getAvatarResId() { return avatarResId; }
}
