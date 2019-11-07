package controllers;

import javafx.scene.layout.Pane;
import models.TripModel;

import java.util.ArrayList;

/**
 * @author Oussama Fahchouch
 */
public class TripController implements Controller {
	private TripModel tripModel;
	
	/**
	 * @author Oussama Fahchouch
	 * @param projectId
	 * @param userId
	 * @param licenseplate
	 * @param startLocation
	 * @param endLocation
	 * @param startKilometergauge
	 * @param endKilometergauge
	 * @param customerId
	 * @return
	 */
	private boolean addTrip(int projectId, int userId, String licenseplate, String startLocation,
			String endLocation, double startKilometergauge, double endKilometergauge, int customerId) {
		return false;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @param tripId
	 * @return
	 */
	private boolean deleteTrip(int tripId) {
		return false;
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
	 * @return ArrayList<String> fetchedTrips
	 */
	private ArrayList<String> getTripsMadeByUser() {
		ArrayList<String> fetchedTrips = new ArrayList<String>();
		return fetchedTrips;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @return headerPane
	 */
	public Pane getHeaderPane() {
		return this.appController.getHeaderPane();
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @return headerPane
	 */
	public Pane getMenuPane() {
		return this.appController.getMenuPane();
	}
}
