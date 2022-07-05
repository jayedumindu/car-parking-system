package controllers;

import db.Drivers;
import db.VehicleManager;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.inParkingVehicleManager;
import models.onDeliveryVehicleManager;

import java.awt.event.WindowEvent;
import java.io.IOException;

public class shiftDetails {

    public ComboBox optionMenu;
    public TableColumn vNumberColumn;
    public TableColumn vTypeColumn;
    public TableColumn thirdColumn;
    public TableColumn fourthColumn;
    public TableView tableView;

    public void initialize() {
        // setting items to the combo-boxes
        ObservableList<String> ob = FXCollections.observableArrayList();
        ob.add("In Parking");
        ob.add("On Delivery");

        optionMenu.setItems(ob);
    }

    public void addVehicleOnAction(ActionEvent actionEvent) throws IOException {
        try{
            mainStage.addVehicles.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/addVehicles.fxml"))));
            mainStage.addVehicles.setTitle("Add New Vehicle");
            mainStage.addVehicles.show();
        }catch (Throwable t){
            t.printStackTrace();
        }

    }

    public void addDriverOnAction(ActionEvent actionEvent) throws IOException {
        try{
            mainStage.addDrivers.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/addDrivers.fxml"))));
            mainStage.addDrivers.setTitle("Add New Driver");
            mainStage.addDrivers.show();
        }catch (Throwable t){
            t.printStackTrace();
        }

    }

    public void logOutOnAction(ActionEvent actionEvent) {
        managementLogIn.shiftDetailsStage.close();
        mainStage.dashStage.show();
    }

    public void setTableInParking(){

        tableView.getItems().clear();
        // setting up columns
        vNumberColumn.setCellValueFactory(new PropertyValueFactory<inParkingVehicleManager,String>("vNumber"));
        vTypeColumn.setCellValueFactory(new PropertyValueFactory<inParkingVehicleManager,String>("vType"));
        thirdColumn.setCellValueFactory(new PropertyValueFactory<inParkingVehicleManager,Integer>("ParkSlot"));
        fourthColumn.setCellValueFactory(new PropertyValueFactory<inParkingVehicleManager,String>("ParkDateAndTime"));

        ObservableList<inParkingVehicleManager> data = FXCollections.observableArrayList();

        for (inParkingVehicleManager pm:
        VehicleManager.inPark) {
            data.add(pm);
        }
        tableView.setItems(data);

    }

    public void setTableDelivery(){

        tableView.getItems().clear();
        // setting up columns
        vNumberColumn.setCellValueFactory(new PropertyValueFactory<onDeliveryVehicleManager,String>("vNumber"));
        vTypeColumn.setCellValueFactory(new PropertyValueFactory<onDeliveryVehicleManager,String>("vType"));
        thirdColumn.setCellValueFactory(new PropertyValueFactory<onDeliveryVehicleManager,String>("driverName"));
        fourthColumn.setCellValueFactory(new PropertyValueFactory<onDeliveryVehicleManager,String>("leftDateAndTime"));

        ObservableList<onDeliveryVehicleManager> data = FXCollections.observableArrayList();
        for (onDeliveryVehicleManager dm:
                VehicleManager.onDelivery) {
            data.add(dm);
        }
        tableView.setItems(data);
    }

    public void optionSelectedOnAction(ActionEvent actionEvent) {


        if(optionMenu.getValue().toString().equals("In Parking")){
            // changing column names
            thirdColumn.setText("Parking Slot");
            fourthColumn.setText("Parked Time");

            setTableInParking();

        }else{
            // changing column names
            thirdColumn.setText("Driver Name");
            fourthColumn.setText("Left Time");

            setTableDelivery();

        }

    }

    public void refreshTablesOnAction(ActionEvent actionEvent) {
        String t = optionMenu.getValue().toString();
        if(t.equals("In Parking")){
            setTableInParking();
        }
    }
}
