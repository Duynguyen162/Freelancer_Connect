package com.example.freelancer_connect.customer_model;

import java.io.Serializable;

public class Service implements Serializable {
    private String portfolioImage;
    private String title;
    private String price;
    private String averageRating;
    private String reviewCount;
    private String categoryName;
    private String description;
    private String serviceArea;
    private String operatingTime;

    public Service() {
    }

    public Service(String portfolioImage, String title, String price, String averageRating, String reviewCount, String categoryName, String description, String serviceArea, String operatingTime) {
        this.portfolioImage = portfolioImage;
        this.title = title;
        this.price = price;
        this.averageRating = averageRating;
        this.reviewCount = reviewCount;
        this.categoryName = categoryName;
        this.description = description;
        this.serviceArea = serviceArea;
        this.operatingTime = operatingTime;
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
}
