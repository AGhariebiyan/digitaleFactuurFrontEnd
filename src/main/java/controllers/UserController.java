package controllers;

import models.UserModel;

public class UserController {

    public AppController appController = AppController.getInstance();
    private UserModel userModel;
    private String username;
    private String password;

    public boolean login (){ return true;}
    public void getUserLoggedIn(){}

    public void authorize(String user, String passwd) {

    }
}