package controllers;

import com.google.gson.Gson;
import io.jsonwebtoken.io.IOException;
import models.UserModel;
import org.apache.commons.io.IOUtils;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * All functionalities from before the user are processed here.
 * Here the call is made to the API. The authorize will return its value based on the response the API returns. to the front end.
 *
 * @author Ali Rezaa Ghariebiyan
 * @version 08-11-2019
 */
public class UserController implements Controller{

    private UserModel userModel;
    
    /**
     * @author Ali Rezaa Ghariebiyan
     */
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

    /**
     * The call is made here. If the upstream is not empty,
     * the object returned by the backend will be stored in the UserModel
     *
     * @author Ali Rezaa Ghariebiyan
     * @version 08-11-2019
     */
    private boolean authorizeUserFromBackEnd(String user, String password) throws IOException, java.io.IOException {

        InputStream userStream = AppController.getInstance().httpRequest("http://localhost:8080/login/"  + user + "/" + password, "GET");

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