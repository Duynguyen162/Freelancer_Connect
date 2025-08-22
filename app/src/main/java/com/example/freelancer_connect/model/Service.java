package com.example.freelancer_connect.model;

import java.io.Serializable;
import java.security.Timestamp;

public class Service implements Serializable {
    private String ownerId;
    private String title;
    private String description;
    private String price;
    private String categoryName;
    private String averageRating;
    private String reviewCount;
    private String serviceArea;
    private String status;
    private String certification;
    private String email;
    private String phone;
    private String contact;
    private String operatingTime;
    private String portfolioImage;
    private Timestamp createdAt;

    public Service() {
        // Bắt buộc cho Firestore
    }

    // Getter & Setter
    public String getOwnerId() { return ownerId; }
    public void setOwnerId(String ownerId) { this.ownerId = ownerId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public String getAverageRating() { return averageRating; }
    public void setAverageRating(String averageRating) { this.averageRating = averageRating; }

    public String getReviewCount() { return reviewCount; }
    public void setReviewCount(String reviewCount) { this.reviewCount = reviewCount; }

    public String getServiceArea() { return serviceArea; }
    public void setServiceArea(String serviceArea) { this.serviceArea = serviceArea; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getCertification() { return certification; }
    public void setCertification(String certification) { this.certification = certification; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    public String getOperatingTime() { return operatingTime; }
    public void setOperatingTime(String operatingTime) { this.operatingTime = operatingTime; }

    public String getPortfolioImage() { return portfolioImage; }
    public void setPortfolioImage(String portfolioImage) { this.portfolioImage = portfolioImage; }


}
