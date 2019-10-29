package controllers;

import javafx.scene.layout.Pane;
import models.VehicleModel;

import java.util.ArrayList;

/**
 * @author Bram de Jong
 */
public class VehicleController implements Controller {
	private VehicleModel vehicleModel;
	
	/**
	 * @author Bram de Jong
	 * @param licenseplate
	 * @param projectId
	 * @param vehicleName
	 * @param vehicleType
	 * @return
	 */
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
	private ArrayList<String> fetchAllVehicles() {
		ArrayList<String> allVehicles = new ArrayList<String>();
		return allVehicles;
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
