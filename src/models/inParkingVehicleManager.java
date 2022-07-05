package models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class inParkingVehicleManager {
    private StringProperty vNumber;
    private StringProperty vType;
    private IntegerProperty parkSlot;
    private StringProperty parkDateAndTime;

    public inParkingVehicleManager(String vNumber, String vType, int parkSlot, String parkDateAndTime) {
        this.vNumber = new SimpleStringProperty(vNumber);
        this.vType = new SimpleStringProperty(vType);
        this.parkSlot = new SimpleIntegerProperty(parkSlot);
        this.parkDateAndTime =  new SimpleStringProperty(parkDateAndTime);
    }

    public String getvNumber() {
        return vNumber.get();
    }

    public StringProperty vNumberProperty() {
        return vNumber;
    }

    public void setvNumber(String vNumber) {
        this.vNumber.set(vNumber);
    }

    public String getvType() {
        return vType.get();
    }

    public StringProperty vTypeProperty() {
        return vType;
    }

    public void setvType(String vType) {
        this.vType.set(vType);
    }

    public int getParkSlot() {
        return parkSlot.get();
    }

    public IntegerProperty parkSlotProperty() {
        return parkSlot;
    }

    public void setParkSlot(int parkSlot) {
        this.parkSlot.set(parkSlot);
    }

    public String getParkDateAndTime() {
        return parkDateAndTime.get();
    }

    public StringProperty parkDateAndTimeProperty() {
        return parkDateAndTime;
    }

    public void setParkDateAndTime(String parkDateAndTime) {
        this.parkDateAndTime.set(parkDateAndTime);
    }
}
