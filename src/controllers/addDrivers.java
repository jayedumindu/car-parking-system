package controllers;

import db.Drivers;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import models.Driver;
import models.inParkingVehicleManager;

public class addDrivers extends CrudUtility{
    public TextField dName;
    public TextField dNIC;
    public TextField dLisNo;
    public TextField dContact;
    public TextArea dAddress;

    public void addDriverOnAction(ActionEvent actionEvent) {
        if(dAddress.getText()==null | dAddress.getText()=="" | dName.getText()==null | dContact.getText()==null | dNIC.getText()==null | dLisNo.getText()==null){
            new Alert(Alert.AlertType.WARNING,"ALl input fields must be filled before submitting!").show();
            return;
        }else{
            Driver d = new Driver(dNIC.getText(),dName.getText(),dLisNo.getText(),dAddress.getText(),dContact.getText(),false);
            boolean isUnique = true;
            for (Driver dr:
                    Drivers.driverList) {
                if(dr.getNIC().equals(d.getNIC()) | dr.getLN().equals(d.getLN())){
                    isUnique=false;
                }
            }
            if(isUnique) {
                Dashboard.newDrivers++;
                Drivers.driverList.add(d);
                Drivers.driverNamesAll.add(d.getName());
                Drivers.driverNamesOnFree.add(d.getName());
                new Alert(Alert.AlertType.INFORMATION, "Driver has been added successfully").show();
                //guidingToDashBoard();
                refresh();
            }
            else{
                new Alert(Alert.AlertType.INFORMATION, "NIC or License Number cannot be duplicated...").show();
            }
        }
    }

    public void refresh(){
        dName.setText(null);
        dNIC.setText(null);
        dLisNo.setText(null);
        dContact.setText(null);
        dAddress.setText(null);
    }

    public void addVehicleOnAction(ActionEvent actionEvent){};
}
