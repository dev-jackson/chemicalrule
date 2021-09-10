package com.example.chemicalrule.model;

public class PublicationModel {
//    id_publication integer primary key autoincrement,
//    name text not null,
//    description text not null,
//    geolaitude text not null,
//    geolength text not null,
//    image blob not null
    private String name;
    private String description;
    private double latitude;
    private double longitude;
    private byte[] image;

    public PublicationModel(){}
    public PublicationModel(String name, String description, double latitude, double longitude, byte[] image) {
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
