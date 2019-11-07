package controllers;

import io.jsonwebtoken.io.IOException;
import models.UserModel;

import java.io.InputStream;

public class UserController implements Controller{

    private UserModel userModel;
    private String username;
    private String password;

    public boolean authorize(String username, String passwd) {
        System.out.println(username + "hoi");

        try{
            if (authorizeUserFromBackEnd(username, passwd) == true){
                System.out.println("authorize enter");
                return true;
            }
            if (authorizeUserFromBackEnd(username, passwd) == false){
                System.out.println("authorize failed");
                return false;
            }

        }
        catch(IOException | java.io.IOException ex){
            System.out.println("Could not login");
        }
        return false;
    }

    private boolean authorizeUserFromBackEnd(String user, String password) throws IOException, java.io.IOException {
        //Make a call to the API to fetch all the  / example projects are shown below.
        InputStream userStream = AppController.getInstance().httpRequest("http://localhost:8080/login/" + user + "/" + password, "GET");
        System.out.println("entered");

//        Map<String, Object> account = new HashMap<>();
//        account.put("username", user);
//        account.put("password", password);
//
//        String json = new Gson().toJson(account);
        if(userStream == null)
            return false;
        else
            return true;
    }
}