package models;

/**
 * @author Oussama Fahchouch
 */
public class TripModel {
	private int tripId;
	private int projectId;
	private int userId;
	private String licenseplate;
	private String startLocation;
	private String endLocation;
	private double startKilometergauge;
	private double endKilometergauge;
	private float startLat;
	private float endLat;
	private float startLong;
	private float endLong;
	
	/**
	 * @author Oussama Fahchouch
	 * @return the tripId
	 */
	public TripModel(int id, int projectId, int userId, String licensePlate, String startLocation, String endLocation, double startKilometergauge, double endKilometergauge) {
		this.tripId = id;
		this.projectId = projectId;
		this.userId = userId;
		this.licenseplate = licensePlate;
		this.startLocation = startLocation;
		this.endLocation = endLocation;
		this.startKilometergauge = startKilometergauge;
		this.endKilometergauge = endKilometergauge;
	}
	
	/**
	 * @author Oussama Fahchouch
	 */
	public int getTripId() {
		return tripId;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @param tripId the tripId to set
	 */
	public void setTripId(int tripId) {
		this.tripId = tripId;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @return the projectId
	 */
	public int getProjectId() {
		return projectId;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @param projectId the projectId to set
	 */
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @return the licenseplate
	 */
	public String getLicenseplate() {
		return licenseplate;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @param licenseplate the licenseplate to set
	 */
	public void setLicenseplate(String licenseplate) {
		this.licenseplate = licenseplate;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @return the startLocation
	 */
	public String getStartLocation() {
		return startLocation;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @param startLocation the startLocation to set
	 */
	public void setStartLocation(String startLocation) {
		this.startLocation = startLocation;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @return the endLocation
	 */
	public String getEndLocation() {
		return endLocation;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @param endLocation the endLocation to set
	 */
	public void setEndLocation(String endLocation) {
		this.endLocation = endLocation;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @return the startKilometergauge
	 */
	public double getStartKilometergauge() {
		return startKilometergauge;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @param startKilometergauge the startKilometergauge to set
	 */
	public void setStartKilometergauge(double startKilometergauge) {
		this.startKilometergauge = startKilometergauge;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @return the endKilometergauge
	 */
	public double getEndKilometergauge() {
		return endKilometergauge;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @param endKilometergauge the endKilometergauge to set
	 */
	public void setEndKilometergauge(double endKilometergauge) {
		this.endKilometergauge = endKilometergauge;
	}
	
	/**
	 * @author Mike van Es
	 */
	public void setStartLat(float startLat){this.startLat = startLat;}
	
	/**
	 * @author Mike van Es
	 */
	public float getStartLat(){return this.startLat;}

	/**
	 * @author Mike van Es
	 */
	public void setEndLat(float endLat){this.endLat = endLat;}
	
	/**
	 * @author Mike van Es
	 */
	public float getEndLat(){return this.endLat;}

	/**
	 * @author Mike van Es
	 */
	public void setStartLong(float startLong){this.startLong = startLong;}
	
	/**
	 * @author Mike van Es
	 */
	public float getStartLong(){return this.startLong;}

	/**
	 * @author Mike van Es
	 */
	public void setEndLong(float endLong){this.endLat = endLong;}
	
	/**
	 * @author Mike van Es
	 */
	public float getEndLong(){return this.endLong;}

}
