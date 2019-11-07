package controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import io.jsonwebtoken.io.IOException;
import models.UserModel;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

public class UserController implements Controller{

    private UserModel userModel;

    public boolean authorize(String username, String passwd) {

        try{
            if (authorizeUserFromBackEnd(username, passwd)){
                return true;
            }
            if (authorizeUserFromBackEnd(username, passwd)){
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

        try {
            String result = IOUtils.toString(userStream, StandardCharsets.UTF_8);

            if(result.contains("[")) {
                Gson gson = new Gson();
                Type jsonObject = new TypeToken<Collection<JsonObject>>() {}.getType();
                Collection<JsonObject> userCollection = gson.fromJson(result, jsonObject);
                System.out.println("in de if");
                for (JsonObject fetchedUser : userCollection) {
                    UserModel tmpModel = new UserModel(
                            Integer.parseInt(fetchedUser.get("userId").toString()),
                            fetchedUser.get("username").toString().replaceAll("^\"|\"$", ""),
                            fetchedUser.get("userToken").toString().replaceAll("^\"|\"$", "")
                            );
                    System.out.println("in de for");
                }

            }

//        try {
//            String result = IOUtils.toString(userStream, StandardCharsets.UTF_8);
//
//
//            if(result.contains("[")) {
//                System.out.println("lol!");
//                Gson gson = new Gson();
//                Type jsonObject = new TypeToken<Collection<JsonObject>>() {}.getType();
//                Collection<JsonObject> users = gson.fromJson(result, jsonObject);
//                System.out.println("for loop");
//                for (JsonObject fetchuser : users) {
//                    UserModel userModel = new UserModel(
//                            Integer.parseInt(fetchuser.get("userId").toString()),
//                            fetchuser.get("username").toString(),
//                            fetchuser.get("password").toString(),
//                            fetchuser.get("userToken").toString());
//                    System.out.println(userModel.getUsername());
//                }
//
//            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

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