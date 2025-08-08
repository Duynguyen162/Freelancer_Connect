package com.example.freelancer_connect.provider;

import java.io.Serializable;

public class Provider implements Serializable {
    private int image;
    private String tilte;
    private String price;
    private String numJobDone;
    private String numStar;

    public Provider(int image, String tilte, String price, String numJobDone, String numStar) {
        this.image = image;
        this.tilte = tilte;
        this.price = price;
        this.numJobDone = numJobDone;
        this.numStar = numStar;
    }

    public String getTilte() {
        return tilte;
    }

    public void setTilte(String tilte) {
        this.tilte = tilte;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNumJobDone() {
        return numJobDone;
    }

    public void setNumJobDone(String numJobDone) {
        this.numJobDone = numJobDone;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNumStar() {
        return numStar;
    }

    public void setNumStar(String numStar) {
        this.numStar = numStar;
    }
}
