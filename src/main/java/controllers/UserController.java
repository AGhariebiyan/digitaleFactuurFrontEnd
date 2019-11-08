package controllers;

import com.google.gson.Gson;
import io.jsonwebtoken.io.IOException;
import models.UserModel;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class UserController implements Controller{

    private UserModel userModel;

    public boolean authorize(String username, String passwd) {

        try{
            if (authorizeUserFromBackEnd(username, passwd)){
                return true;
            }else{
                return false;
            }

        }
        catch(IOException | java.io.IOException ex){
            System.out.println("Could not login");
        }
        return false;
    }

    private boolean authorizeUserFromBackEnd(String user, String password) throws IOException, java.io.IOException {

        InputStream userStream = AppController.getInstance().httpRequest("http://localhost:8080/login/"  + user + "/" + password, "GET");
        System.out.println("http://localhost:8080/login/"  + user + "/" + password);

        if(userStream != null) {
            try {
                String result = IOUtils.toString(userStream, StandardCharsets.UTF_8);
                Gson gson = new Gson();
                UserModel userModel = gson.fromJson(result, UserModel.class);

                appController.setCurrentUser(userModel);

                return true;
            } catch (IOException ex) {
                ex.printStackTrace();
                return false;
            }
        }else{
            return false;
        }
    }
}