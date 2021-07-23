package com.example.chemicalrule.ui;

import android.graphics.drawable.Drawable;

public class ListElement {
    public int imageSrc;
    public String color;
    public String name;
    public String address;
    public String review;
    public String type;

    public ListElement(int imageSrc,String color, String name, String address, String review, String type) {
        this.imageSrc = imageSrc;
        this.color = color;
        this.name = name;
        this.address = address;
        this.review = review;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getImageSrc() {
        return imageSrc;
    }
    public void setImageSrc(int imageSrc) {
        this.imageSrc = imageSrc;
    }
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
