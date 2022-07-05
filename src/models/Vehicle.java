package models;

public class Vehicle{
    private String vNumber;
    private Double maxWeight;
    private int passengers;
    private String vehicleType;
    private boolean isParked = true;
    private String driver;
    private int parkingSlot;

    public Vehicle(String vNumber, Double maxWeight, int passengers, String vehicleType, boolean isParked) {
        this.vNumber = vNumber;
        this.maxWeight = maxWeight;
        this.passengers = passengers;
        this.vehicleType = vehicleType;
        this.isParked = isParked;
    }

    public Vehicle(String vNumber, Double maxWeight, int passengers, String vehicleType) {
        this.vNumber = vNumber;
        this.maxWeight = maxWeight;
        this.passengers = passengers;
        this.vehicleType = vehicleType;
    }

    public Vehicle(){

    }

    public String getvNumber() {
        return vNumber;
    }

    public void setvNumber(String vNumber) {
        this.vNumber = vNumber;
    }

    public Double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(Double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vNumber='" + vNumber + '\'' +
                ", maxWeight=" + maxWeight +
                ", passengers=" + passengers +
                ", vehicleType='" + vehicleType + '\'' +
                '}';
    }

    public boolean isParked() {
        return isParked;
    }

    public void setParked(boolean parked) {
        isParked = parked;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public int getParkingSlot() {
        return parkingSlot;
    }

    public void setParkingSlot(int parkingSlot) {
        this.parkingSlot = parkingSlot;
    }
}
