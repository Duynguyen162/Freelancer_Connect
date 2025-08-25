package com.example.freelancer_connect.customer_model;

import java.io.Serializable;

public class Service implements Serializable {
    String id;
    private String portfolioImage;
    private String title;
    private String price;
    private String averageRating;
    private String reviewCount;
    private String numContact;
    private String categoryName;
    private String description;
    private String serviceArea;
    private String operatingTime;
    private String phone;
    private String email;

    public Service() {
    }

    public Service(String id, String portfolioImage, String title, String price, String averageRating, String reviewCount, String categoryName, String description, String serviceArea, String operatingTime, String phone, String email, String numContact) {
        this.id = id;
        this.portfolioImage = portfolioImage;
        this.title = title;
        this.price = price;
        this.averageRating = averageRating;
        this.reviewCount = reviewCount;
        this.categoryName = categoryName;
        this.description = description;
        this.serviceArea = serviceArea;
        this.operatingTime = operatingTime;
        this.phone = phone;
        this.email = email;
        this.numContact = numContact;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPortfolioImage() {
        return portfolioImage;
    }

    public void setPortfolioImage(String portfolioImage) {
        this.portfolioImage = portfolioImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }

    public String getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(String reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getServiceArea() {
        return serviceArea;
    }

    public void setServiceArea(String serviceArea) {
        this.serviceArea = serviceArea;
    }

    public String getOperatingTime() {
        return operatingTime;
    }

    public void setOperatingTime(String operatingTime) {
        this.operatingTime = operatingTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumContact() {
        return numContact;
    }

    public void setNumContact(String numContact) {
        this.numContact = numContact;
    }
}
