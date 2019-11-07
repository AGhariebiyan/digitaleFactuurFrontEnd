package controllers;

import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

import javafx.scene.Node;

import javafx.scene.layout.Pane;


/**
 * @author Fifi
 *
 */
public class DashboardController implements Controller {
	
	public void setHeaderPane(Pane headerPane) {
		Controller.appController.setHeaderPane(headerPane);
	}
	
	public void setMenuPane(Pane menuPane) {
		Controller.appController.setMenuPane(menuPane);
	}
	
    public String getStringTotalProjectsFromBackEnd(){
        // To store our response
        
        String line;  
        StringBuilder content = null;

        // Get the input stream of the connection
        try {
            //BufferedReader input = new BufferedReader(new InputStreamReader(AppController.httpRequest("http://localhost:8080/trips", "GET")));
             InputStream totalProjectsStream = AppController.getInstance().httpRequest("http://localhost:8080/trips/user/project", "GET");
                
             String result = IOUtils.toString(totalProjectsStream, StandardCharsets.UTF_8);
             
        
            /*content = new StringBuilder();
            while ((line = input.readLine()) != null) {
                // Append each line of the response and separate them
                content.append(line);
                content.append(System.lineSeparator());
                }*/
                return result;
          
            }catch(IOException ex) {
            ex.printStackTrace();
            }
        //return content.toString();
        return null;
    }
    
    public String getStringTotalTripsFromBackEnd(){
        // To store our response
        
        String line;  
        StringBuilder content = null;

        // Get the input stream of the connection
        try {
            //BufferedReader input = new BufferedReader(new InputStreamReader(AppController.httpRequest("http://localhost:8080/trips", "GET")));
             InputStream totalProjectsStream = AppController.getInstance().httpRequest("http://localhost:8080/trips/user", "GET");
                
             String result = IOUtils.toString(totalProjectsStream, StandardCharsets.UTF_8);
             
        
            /*content = new StringBuilder();
            while ((line = input.readLine()) != null) {
                // Append each line of the response and separate them
                content.append(line);
                content.append(System.lineSeparator());
                }*/
                return result;
          
            }catch(IOException ex) {
            ex.printStackTrace();
            }
        //return content.toString();
        return null;
    }
	
}
