package com.example.chemicalrule.ui;

public class ListElement {
    public String color;
    public String name;
    public String address;
    public String review;

    public ListElement(String color, String name, String address, String review) {
        this.color = color;
        this.name = name;
        this.address = address;
        this.review = review;
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
