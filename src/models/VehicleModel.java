package models;

/**
 * @author Bram de Jong
 */
public class VehicleModel {
    private int userId;
    private String licensePlate;
    private String vehicleName;
    private String vehicleType;
    private int totalTrips;

    public VehicleModel(int userId, String licensePlate, String vehicleName, String vehicleType, int totalTrips){
        this.userId = userId;
        this.licensePlate = licensePlate;
        this.vehicleName = vehicleName;
        this.vehicleType = vehicleType;
        this.totalTrips = totalTrips;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int vehicleId) {
        this.userId = userId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensplate(String licensPlate) {
        this.licensePlate = licensPlate;
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