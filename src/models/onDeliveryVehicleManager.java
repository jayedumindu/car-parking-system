package models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class onDeliveryVehicleManager {
    private StringProperty vNumber;
    private StringProperty vType;
    private StringProperty driverName;
    private StringProperty leftDateAndTime;

    public onDeliveryVehicleManager (String vNumber, String vType, String driverName, String leftDateAndTime) {
        this.vNumber = new SimpleStringProperty(vNumber);
        this.vType = new SimpleStringProperty(vType);
        this.driverName = new SimpleStringProperty(driverName);
        this.leftDateAndTime = new SimpleStringProperty(leftDateAndTime);
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

    public String getDriverName() {
        return driverName.get();
    }

    public StringProperty driverNameProperty() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName.set(driverName);
    }

    public String getLeftDateAndTime() {
        return leftDateAndTime.get();
    }

    public StringProperty leftDateAndTimeProperty() {
        return leftDateAndTime;
    }

    public void setLeftDateAndTime(String leftDateAndTime) {
        this.leftDateAndTime.set(leftDateAndTime);
    }
}
