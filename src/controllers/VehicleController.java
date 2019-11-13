package controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import javafx.scene.layout.Pane;
import models.VehicleModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.IOUtils;

/**
 * @author Bram de Jong
 */
public class VehicleController implements Controller {
    private VehicleModel vehicleModel;
    ArrayList<VehicleModel> vehicleModels = new ArrayList();
    private TripController tripController = new TripController();

    /**
     * @author Bram de Jong
     * @param licenseplate
     * @param projectId
     * @param vehicleName
     * @param vehicleType
     * @return
     */
    public boolean addVehicle(String licenseplate, int projectId, String vehicleName, String vehicleType) {
    	if(licenseplate.contains(" "))
			licenseplate = licenseplate.replace(" ", "%20");

		if(vehicleName.contains(" "))
			vehicleName = vehicleName.replace(" ", "%20");

		if(vehicleType.contains(" "))
			vehicleType = vehicleType.replace(" ", "%20");
		
        AppController.httpRequest("http://localhost:8080/vehicles/vehicle/add/for-user/"
        + AppController.getInstance().getCurrentUser().getUserId() + "/" 
		+ 0 + "/"
        + licenseplate + "/"
		+ vehicleName + "/"
        + vehicleType,"POST");
        AppController.loadView("views.VehicleOverviewView", "createView");
        return true;
    }

    /**
     * @author Bram de Jong
     * @param
     * @return
     */
    public boolean deleteVehicle(int index) {
        AppController.httpRequest("http://localhost:8080/vehicles/delete/" +
		vehicleModels.get(index).getLicensePlate(),"DELETE");
        return false;
    }

    /**
     * @author Bram de Jong
     * @param lincenseplate
     * @return totalTrips
     */
    private int retrieveAmountOfTripsMade(String lincenseplate) {
        return vehicleModel.getTotalTrips();
    }


    public JsonObject parse(String json) throws JsonSyntaxException {
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();

        return jsonObject;
    }

    /**
     * @author Bram de Jong
     * @return ArrayList<String> allVehicles
     */
    public ArrayList<VehicleModel> fetchAllVehicles() {
        InputStream stream = AppController.httpRequest("http://localhost:8080/vehicles/user/" + AppController.getInstance().getCurrentUser().getUserId(),"GET");
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        int x = 0;
        try {
            String line = reader.readLine();
            line = line.replace(",{","~{");
            line = line.replace("[","");
            line = line.replace("]","");
            String[] parts = line.split("~");
            JsonObject uitkomst;
            while (x != parts.length) {
                uitkomst = parse(parts[x]);
                VehicleModel tempModel = new VehicleModel(uitkomst.get("userId").getAsInt(), uitkomst.get("licensePlate").getAsString(), uitkomst.get("vehicleName").getAsString(), uitkomst.get("vehicleType").getAsString(), uitkomst.get("totalTrips").getAsInt());
                vehicleModels.add(tempModel);
                x++;
            }
        } catch (Exception IOException) {

        }
        return vehicleModels;
    }

    /**
     * @author Bram de Jong
     * @param tripId
     * @return licenseplate
     */
    private String fetchVehicleUsedForTrip(int tripId) {
        return vehicleModel.getLicensePlate();
    }

    /**
     * @author Bram de Jong
     * @return headerPane
     */
    public Pane getHeaderPane() {
        return Controller.appController.getHeaderPane();
    }

    /**
     * @author Bram de Jong
     * @return headerPane
     */
    public Pane getMenuPane() {
        return Controller.appController.getMenuPane();
    }
    
    /**
     * @author Oussama Fahchouch
     * @return List<Integer> fetchedUniqueProjectIds
     */
    public List<Integer> fetchAllUniqueProjectIds() {
    	return tripController.fetchAllUniqueProjectIds();
    }
    
    /**
     * @author Oussama Fahchouch
     * @return ArrayList<String> uniqueLicenseplates
     */
    public List<String>  fetchAllUniqueLicenseplates(){
    	List<String> fetchedUniqueLicenseplates;
        AppController.getInstance();
		InputStream tripStream = AppController.httpRequest("http://localhost:8080/vehicles/fetch/unique-licenseplates/" + 
        AppController.getInstance().getCurrentUser().getUserId(), "GET");
        
        try {
            String result = IOUtils.toString(tripStream, StandardCharsets.UTF_8);
            
            if(result.contains("[")) {
                Gson gson = new Gson();
                Type jsonObject = new TypeToken<Collection<String>>() {}.getType();
                Collection<String> allUniqueLicenseplatesColl = gson.fromJson(result, jsonObject);

                fetchedUniqueLicenseplates = (List<String>) allUniqueLicenseplatesColl;
                
                for(String lplate :fetchedUniqueLicenseplates) {
                	System.out.println(lplate);
                }
                
                return fetchedUniqueLicenseplates;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}