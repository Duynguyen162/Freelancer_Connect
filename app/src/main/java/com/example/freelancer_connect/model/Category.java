package com.example.freelancer_connect.model;

public class Category {
    private String name;
    private String description;
    private String icon; // URL ảnh
    private String id;
    public Category() {
        // Bắt buộc có constructor rỗng cho Firestore
    }

    public Category(String name, String description, String icon) {
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.id = id;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }
}
