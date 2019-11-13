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
	
	/**
	 * @author Fifi
	 *
	 */
	public void setHeaderPane(Pane headerPane) {
		Controller.appController.setHeaderPane(headerPane);
	}
	
	/**
	 * @author Fifi
	 *
	 */
	public void setMenuPane(Pane menuPane) {
		Controller.appController.setMenuPane(menuPane);
	}
	
	/**
	 * @author Fifi
	 *
	 */
    public static String getStringTotalProjectsFromBackEnd(){
        try {
        	AppController.getInstance();
			InputStream totalProjectsStream = AppController.httpRequest("http://localhost:8080/trips/amount-of-projects/user/" +
			Controller.appController.getCurrentUser().getUserId(), "GET");
        	
             String result = IOUtils.toString(totalProjectsStream, StandardCharsets.UTF_8);
             return result;
          
            }catch(IOException ex) {
            ex.printStackTrace();
            }
        return null;
    }
    
    /**
     * @author Fifi
     *
     */
    public String getStringTotalTripsFromBackEnd(){

        try {
        	AppController.getInstance();
			InputStream totalProjectsStream = AppController.httpRequest("http://localhost:8080/trips/amount-of-trips/user/" + 
        	Controller.appController.getCurrentUser().getUserId(), "GET");                
             String result = IOUtils.toString(totalProjectsStream, StandardCharsets.UTF_8);

            return result;
          
            }catch(IOException ex) {
            ex.printStackTrace();
            }
        return null;
    }
	
}