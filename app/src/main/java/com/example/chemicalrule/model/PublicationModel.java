package com.example.chemicalrule.model;

public class PublicationModel {
//    id_publication integer primary key autoincrement,
//    name text not null,
//    description text not null,
//    geolaitude text not null,
//    geolength text not null,
//    image blob not null
    private  int id_publication;
    private String name;
    private String description;
    private String latitude;
    private String longitude;
    private byte[] image;

    public PublicationModel(){}

    public PublicationModel(int id_publication, String name, String description, String latitude, String longitude, byte[] image) {
        this.id_publication = id_publication;
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.image = image;
    }

    public int getId_publication() {
        return id_publication;
    }

    public void setId_publication(int id_publication) {
        this.id_publication = id_publication;
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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
