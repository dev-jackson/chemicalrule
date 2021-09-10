package com.example.chemicalrule.model;

import java.sql.Blob;

public class UserModel {
    private int id;
    private String username;
    private String email;
    private String password;
    private String birth_date;
    private int type;

    public UserModel(){}

    public UserModel(int id,int type) {
        this.id = id;
        this.type = type;
    }

    public UserModel(int id, String username, String email, String password, String birth_date, int type) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.birth_date = birth_date;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }
}
