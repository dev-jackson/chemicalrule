package com.example.chemicalrule.controllers;

import com.example.chemicalrule.db.OpenHelperSql;
import com.example.chemicalrule.model.UserModel;

import java.util.ArrayList;

public class UserController {

    public UserController(){}

    public ArrayList<UserModel> getAllUser(){
        return null;
    }

    public boolean createNewUser(UserModel user){
        return true;
    }

    public boolean deleteUserById(int id){
        return true;
    }
    public boolean updateUser(UserModel user){
        return true;
    }

}
