package com.example.freelancer_connect.model;

public class User {
    private String name;
    private String code;
    private String email;
    private String role;
    private String phone;
    private String address;
    private int avatarResId;   // fallback (code cũ)
    private String avatarUrl;  // mới (Firebase)

    // ⚠️ Bắt buộc cho Firebase
    public User() {}

    public User(String name, String code, String email, String role,
                String phone, String address, int avatarResId) {
        this.name = name;
        this.code = code;
        this.email = email;
        this.role = role;
        this.phone = phone;
        this.address = address;
        this.avatarResId = avatarResId;
    }

    // Getter & Setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public int getAvatarResId() { return avatarResId; }
    public void setAvatarResId(int avatarResId) { this.avatarResId = avatarResId; }

    public String getAvatarUrl() { return avatarUrl; }
    public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }
}