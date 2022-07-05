package controllers;

import db.Drivers;
import db.ParkingSlots;
import db.VehicleManager;
import db.Vehicles;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import models.Driver;
import models.ParkingSlot;
import models.Vehicle;
import models.inParkingVehicleManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class addVehicles extends CrudUtility {

    public TextField vNumber;
    public TextField vWeight;
    public TextField vNoOfPassengers;
    public ComboBox vTypeSelector;

    public void initialize(){
        vTypeSelector.getItems().add("Bus");
        vTypeSelector.getItems().add("Van");
        vTypeSelector.getItems().add("Cargo Lorry");
    }

    public void addDriverOnAction(ActionEvent actionEvent){
    };

    public void addVehicleOnAction(ActionEvent actionEvent) {
        boolean continuee = vehicleExceptionHandling();
        if (Dashboard.freeSlots > 0 & continuee) {
            boolean found = false;
            boolean isUnique = true;
            String vt = vTypeSelector.getValue().toString();
            // if its a van, then checking whether van-slots are available
            if (vt.equals("Van")) {
                outer : for (ParkingSlot p :
                        ParkingSlots.slots) {
                    if (p.isFree()) {
                        for (Integer i : ParkingSlots.vanSlots) {
                            if (p.getSlotNum() == i) {
                                // found a slot
                                found=true;
                                Vehicle v = new Vehicle(vNumber.getText(), Double.parseDouble(vWeight.getText()), Integer.parseInt(vNoOfPassengers.getText()), vTypeSelector.getValue().toString());
                                for (Vehicle V:
                                     Vehicles.vehicleList) {
                                    if(v.getvNumber().equals(V.getvNumber())){
                                        isUnique=false;
                                    }
                                }
                                if(isUnique){
                                    Dashboard.newVehicles++;
                                    Dashboard.freeSlots--;
                                    v.setParkingSlot(i);
                                    Vehicles.vehicleList.add(v);
                                    Vehicles.vehicleNumList.add(v.getvNumber());
                                    p.setFree(false);
                                    // adding vehicle to vehicle-manager
                                    addingToVehicleManager(v,i);
                                    //guidingToDashBoard();
                                    break outer;
                                }

                            }
                        }
                    }
                }
            } else if (vt.equals("Cargo Lorry")) {
                outer : for (ParkingSlot p :
                        ParkingSlots.slots) {
                    if (p.isFree()) {
                        for (Integer i : ParkingSlots.lorrySlots) {
                            if (p.getSlotNum() == i) {
                                // found a slot
                                found=true;
                                Vehicle v = new Vehicle(vNumber.getText(), Double.parseDouble(vWeight.getText()), Integer.parseInt(vNoOfPassengers.getText()), vTypeSelector.getValue().toString());
                                for (Vehicle V:
                                        Vehicles.vehicleList) {
                                    if(v.getvNumber().equals(V.getvNumber())){
                                        isUnique=false;
                                    }
                                }
                                if(isUnique){
                                    Dashboard.newVehicles++;
                                    Dashboard.freeSlots--;
                                    v.setParkingSlot(i);
                                    Vehicles.vehicleList.add(v);
                                    Vehicles.vehicleNumList.add(v.getvNumber());
                                    p.setFree(false);
                                    addingToVehicleManager(v,i);
                                    //guidingToDashBoard();
                                    break outer;
                                }

                            }
                        }
                    }
                }

            } else {
                if(ParkingSlots.slots.get(13).isFree()){
                    found=true;
                    Vehicle v = new Vehicle(vNumber.getText(), Double.parseDouble(vWeight.getText()), Integer.parseInt(vNoOfPassengers.getText()), vTypeSelector.getValue().toString());
                    for (Vehicle V:
                            Vehicles.vehicleList) {
                        if(v.getvNumber().equals(V.getvNumber())){
                            isUnique=false;
                        }
                    }
                    if(isUnique){
                        Dashboard.newVehicles++;
                        Dashboard.freeSlots--;
                        v.setParkingSlot(14);
                        Vehicles.vehicleList.add(v);
                        Vehicles.vehicleNumList.add(v.getvNumber());
                        ParkingSlots.slots.get(13).setFree(false);
                        addingToVehicleManager(v,14);
                        //guidingToDashBoard();
                    }
                }
            }

            if(!isUnique){
                new Alert(Alert.AlertType.WARNING, "Vehicle number cannot be duplicated!").show();
                refresh();
            }
            else if(!found){
                new Alert(Alert.AlertType.WARNING, "No available parking slots for the vehicle type!").show();
                refresh();
            }
            else{
                new Alert(Alert.AlertType.INFORMATION, "Vehicle has been added successfully").show();
                refresh();
            }

        } else {
            new Alert(Alert.AlertType.WARNING, "No available parking slots to add the vehicle!").show();
            refresh();
        }

    }

    public boolean vehicleExceptionHandling(){
        if(vNumber.getText()==null | vWeight.getText()==null | vNumber.getText()==null | vTypeSelector.getValue()==null){
            new Alert(Alert.AlertType.WARNING,"All inputs fields must be filled before submitting!").show();
            return false;
        }else{
            return true;
        }
    }

    public void addingToVehicleManager(Vehicle v, int pslot){
        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatted =  today.format(myFormatObj).toString();
        inParkingVehicleManager p = new inParkingVehicleManager(v.getvNumber(),v.getVehicleType(),pslot,formatted);
        VehicleManager.inPark.add(p);
    }

    public void refresh(){
        vNumber.setText(null);
        vWeight.setText(null);
        vNoOfPassengers.setText(null);
        vTypeSelector.setValue(null);
    }
}
