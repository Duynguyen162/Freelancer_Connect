package com.example.freelancer_connect;

public class Service {
    private int serviceImage;
    private String serviceTitle;
    private String servicePrice;
    private String serviceStar;

    public Service(int serviceImage, String serviceTitle, String servicePrice, String serviceStar) {
        this.serviceImage = serviceImage;
        this.serviceTitle = serviceTitle;
        this.servicePrice = servicePrice;
    }

    public int getServiceImage() {
        return serviceImage;
    }

    public void setServiceImage(int serviceImage) {
        this.serviceImage = serviceImage;
    }

    public String getServiceTitle() {
        return serviceTitle;
    }

    public void setServiceTitle(String serviceTitle) {
        this.serviceTitle = serviceTitle;
    }

    public String getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(String servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getServiceStar() {
        return serviceStar;
    }

    public void setServiceStar(String serviceStar) {
        this.serviceStar = serviceStar;
    }
}
