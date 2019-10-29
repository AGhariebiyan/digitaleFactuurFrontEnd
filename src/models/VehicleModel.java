package models;

/**
 * @author Bram de Jong
 */
public class VehicleModel {
	private int vehicleId;
	private String licensplate;
	private String vehicleName;
	private String vehicleType;
	private int totalTrips;


	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getLicensplate() {
		return licensplate;
	}

	public void setLicensplate(String licensplate) {
		this.licensplate = licensplate;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public int getTotalTrips() {
		return totalTrips;
	}

	public void setTotalTrips(int totalTrips) {
		this.totalTrips = totalTrips;
	}
}
