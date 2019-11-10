package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import javafx.scene.layout.Pane;
import models.TripModel;

/**
 * @author Oussama Fahchouch
 */
public class TripController implements Controller {
	/**
	 * @author Oussama Fahchouch
	 * @param projectId
	 * @param licenseplate
	 * @param startLocation
	 * @param endLocation
	 * @return
	 */
	public void addTripForProject(int projectId, String licenseplate, String startLocation, String endLocation) {
		if(licenseplate.contains(" "))
			licenseplate = licenseplate.replace(" ", "%20");

		if(startLocation.contains(" "))
			startLocation = startLocation.replace(" ", "%20");

		if(endLocation.contains(" "))
			endLocation = endLocation.replace(" ", "%20");
		
		AppController.getInstance();
		AppController.httpRequest("http://localhost:8080/trips/trip/add/for-project/"
				+ projectId + "/"
				+ AppController.getInstance().getCurrentUser().getUserId() + "/"
			    + licenseplate + "/" 
				+ startLocation + "/" 
			    + endLocation + "/0/0", "POST");
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @param licenseplate
	 * @param startLocation
	 * @param endLocation
	 * @return
	 */
	public void addTripByUser(String licenseplate, String startLocation, String endLocation) {
		if(licenseplate.contains(" "))
			licenseplate = licenseplate.replace(" ", "%20");

		if(startLocation.contains(" "))
			startLocation = startLocation.replace(" ", "%20");

		if(endLocation.contains(" "))
			endLocation = endLocation.replace(" ", "%20");

		AppController.getInstance();
		AppController.httpRequest("http://localhost:8080/trips/trip/add/for-user/" 
				+ AppController.getInstance().getCurrentUser().getUserId() + "/"
			    + licenseplate + "/" 
				+ startLocation + "/" 
			    + endLocation + "/0/0", "POST");
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @param tripId
	 * @return
	 */
	public void deleteTrip(int tripId) {
        AppController.getInstance();
		AppController.httpRequest("http://localhost:8080/trips/delete/" + Integer.toString(tripId), "DELETE");

	}
	
	/**
	 * @author Oussama Fahchouch
	 * @param startKilometergauge
	 * @param endKilometergauge
	 * @return
	 */
	private double calculateDistance(double startKilometergauge, double endKilometergauge) {
		return 0.0;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @return headerPane
	 */
	public Pane getHeaderPane() {
		return Controller.appController.getHeaderPane();
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @return headerPane
	 */
	public Pane getMenuPane() {
		return Controller.appController.getMenuPane();
	}
	
	/**
     * @author Oussama Fahchouch
     * @return ArrayList<Integer> uniqueProjectIds
     */
    public List<Integer>  fetchAllUniqueProjectIds(){
    	List<Integer> fetchedUniqueProjectIds;
        AppController.getInstance();
		InputStream tripStream = AppController.httpRequest("http://localhost:8080/trips/fetch/unique-projectids", "GET");
        
        try {
            String result = IOUtils.toString(tripStream, StandardCharsets.UTF_8);
            
            if(result.contains("[")) {
                Gson gson = new Gson();
                Type jsonObject = new TypeToken<Collection<Integer>>() {}.getType();
                Collection<Integer> allUniqueProjectIdsColl = gson.fromJson(result, jsonObject);

                fetchedUniqueProjectIds = (List<Integer>) allUniqueProjectIdsColl;
                
                return fetchedUniqueProjectIds;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
	
    /**
     * @author Mike van Es
     * @author Oussama Fahchouch
     */
    public ArrayList<TripModel>  fetchTrips(){
    	ArrayList<TripModel> fetchedTrips = new ArrayList<TripModel>();
        AppController.getInstance();
		InputStream tripStream = AppController.httpRequest("http://localhost:8080/trips/user/" 
    	+ AppController.getInstance().getCurrentUser().getUserId(), "GET");
        
        try {
            String result = IOUtils.toString(tripStream, StandardCharsets.UTF_8);
            
            if(result.contains("[")) {
                Gson gson = new Gson();
                Type jsonObject = new TypeToken<Collection<JsonObject>>() {}.getType();
                Collection<JsonObject> tripColl = gson.fromJson(result, jsonObject);

                for (JsonObject trip : tripColl) {
                  TripModel tmpModel = new TripModel(Integer.parseInt(trip.get("tripId").toString()), 
                		  Integer.parseInt(trip.get("projectId").toString()), 
                		  Integer.parseInt(trip.get("userId").toString()), 
                		  trip.get("licensePlate").toString().replaceAll("^\"|\"$", ""), 
                		  trip.get("startLocation").toString().replaceAll("^\"|\"$", ""), 
                		  trip.get("endLocation").toString().replaceAll("^\"|\"$", ""), 
                		  Double.parseDouble(trip.get("startKilometergauge").toString()), 
                		  Double.parseDouble(trip.get("endKilometergauge").toString()));

                  fetchedTrips.add(tmpModel);
                }
                return fetchedTrips;
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    
}