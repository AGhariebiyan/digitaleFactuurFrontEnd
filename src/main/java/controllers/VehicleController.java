package controllers;

import javafx.scene.layout.Pane;
import models.VehicleModel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Bram de Jong
 */
public class VehicleController implements Controller {
    private VehicleModel vehicleModel;

    /**
     * @author Bram de Jong
//     * @param licenseplate
//     * @param projectId
//     * @param vehicleName
//     * @param vehicleType
     * @return
     */
    public VehicleController(){
        this.fetchAllVehicles();
    }
    private boolean addVehicle(String licenseplate, int projectId, String vehicleName, String vehicleType) {
        return false;
    }

    /**
     * @author Bram de Jong
     * @param licensplate
     * @return
     */
    private boolean deleteVehicle(String licensplate) {
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

    /**
     * @author Bram de Jong
     * @return ArrayList<String> allVehicles
     */
    private String fetchAllVehicles() {
        InputStream test = appController.httpRequest("http://localhost:8080/vehicles","GET");
        BufferedReader reader = new BufferedReader(new InputStreamReader(test));
        int x = 0;
        try {
            String line = reader.readLine();
            System.out.println(line);
            line = line.replace(",{","");
            line = line.replace("[","");
            line = line.replace("{","");
            line = line.replace("]","");
            System.out.println(line);
            String[] parts = line.split("}");
            System.out.println(parts[0]);
            while (x != parts.length) {

            }

        } catch (Exception IOException) {

        }
        return null;
    }

    /**
     * @author Bram de Jong
     * @param tripId
     * @return licenseplate
     */
    private String fetchVehicleUsedForTrip(int tripId) {
        return vehicleModel.getLicensplate();
    }

    /**
     * @author Bram de Jong
     * @return headerPane
     */
    public Pane getHeaderPane() {
        return this.appController.getHeaderPane();
    }

    /**
     * @author Bram de Jong
     * @return headerPane
     */
    public Pane getMenuPane() {
        return this.appController.getMenuPane();
    }
}