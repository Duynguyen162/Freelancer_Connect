package com.example.freelancer_connect.model;

public class User {
    private String name;
    private String code;
    private int avatarResId; // resource id của hình ảnh avatar

    public User(String name, String code, int avatarResId) {
        this.name = name;
        this.code = code;
        this.avatarResId = avatarResId;
    }
    public String getName() { return name; }
    public String getCode() { return code; }
    public int getAvatarResId() { return avatarResId; }


}
