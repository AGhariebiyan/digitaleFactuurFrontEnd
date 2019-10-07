package controllers;

import models.TripModel;
import views.View;

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
}
