package controllers;

import models.UserModel;

import java.io.InputStream;

public class UserController implements Controller{

    private UserModel userModel;
    private String username;
    private String password;

    public boolean login (){ return true;}
    public void getUserLoggedIn(){}

    public boolean authorize(String user, String passwd) {
        System.out.println(user + "hoi");
        if (authorizeUserFromBackEnd(user, passwd)){
            System.out.println("autorize enter");
            return true;
        }
        else{
            return false;
        }
    }

    private boolean authorizeUserFromBackEnd(String user, String password){
        //Make a call to the API to fetch all the  / example projects are shown below.
        InputStream userStream = AppController.getInstance().httpRequest("http://localhost:8080/login/" + user + "/" + password, "GET");
        System.out.println("entered");
//        Map<String, Object> account = new HashMap<>();
//        account.put("username", user);
//        account.put("password", password);
//
//        String json = new Gson().toJson(account);

        return true;
    }
}