package controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import javafx.scene.layout.Pane;
import models.VehicleModel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author Bram de Jong
 */
public class VehicleController implements Controller {
    private VehicleModel vehicleModel;
    ArrayList<VehicleModel> vehicleModels = new ArrayList();

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
        System.out.println(vehicleModels.get(index).getLicensePlate());
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
        InputStream stream = AppController.httpRequest("http://localhost:8080/vehicles","GET");
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
}