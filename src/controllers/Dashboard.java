package controllers;

import db.Drivers;
import db.ParkingSlots;
import db.Vehicles;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.*;
import db.VehicleManager;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Dashboard {

    public Text txtDate;
    public Text txtTime;
    public Text parkingNumber;
    public ComboBox selectVehicle;
    public ComboBox selectDriver;
    public Text txtVehicleType;
    public Button parkbtn;
    public Button deliverybtn;
    public static Stage loginStage = new Stage();
    public static int freeSlots = 0;
    public static int newDrivers = 0;
    public static int newVehicles = 0;

    // date-time, driver and vehicle initialization
    public void initialize(){
        Timer timer = new Timer();
        int begin = 0;
        int timeInterval = 1000;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int count = 0;
                for (String dt:calDateAndTime()) {
                    if(count==0){
                        txtDate.setText(dt);
                        count++;
                    }else{
                        txtTime.setText(dt);
                    }
                }
            }
        }, begin, timeInterval);


        addVehiclesAndDrivers();

        addingParkingSlots();

        addingVehiclesToParkingSlots();

        managingVehiclesOnStart();

    }

    // adding drivers and vehicles to db
    private void addVehiclesAndDrivers(){

        // adding drivers
        ArrayList<Driver> arrDriver = Drivers.driverList;
        arrDriver.add(new Driver("7835348345V","Sumith Kumara","B6474845","Panadura","0725637456",false));
        arrDriver.add(new Driver("8826253734V","Amila Pathirana","B3354674 ","Galle","0717573583",false));
        arrDriver.add(new Driver("9283289272V","Jithmal Perera ","B3674589","Horana","0772452457",false));
        arrDriver.add(new Driver("9425245373V","Sumith Dissanayaka","B8366399","Kaluthara","0782686390",false));
        arrDriver.add(new Driver("8976544373V","Sumanasiri Herath","B3537538","Beruwala","0772534436",false));
        arrDriver.add(new Driver("9173537839V","Awantha Fernando","B3554789","Colombo 5","0714534356",false));
        arrDriver.add(new Driver("9573536833V","Charith Sudara","B6835836","Baththaramulla","0771536662",false));
        arrDriver.add(new Driver("9362426738V","Prashan Dineth","B2683536","Wadduwa","0715353434",false));
        arrDriver.add(new Driver("9162353436V","Chethiya Dilan","B6836836","Panadura","0772436737",false));
        arrDriver.add(new Driver("9255556343V","Dushantha Perera","B3334435","Matara","0777245343",false));
        arrDriver.add(new Driver("8735354355V","Sumith Udayanga","B3573783","Galle","0703635442",false));
        arrDriver.add(new Driver("9026344373V","Dinesh Udara","B5343783","Hettimulla","0713456878",false));
        arrDriver.add(new Driver("9692653338V","Udana Chathuranga","B7888632","Kottawa","0772442444",false));
        arrDriver.add(new Driver("9124537733V","Mohommad Riaz","B3638537","Kaluthara","0777544222",false));
        arrDriver.add(new Driver("9563524267V","Sandun Kumara","B2263333","Panadura","0772325544",false));
        arrDriver.add(new Driver("9135343537V","Priyanga Perera","B3853753","Matara","0723344562",false));

        // updating combo-box
        for (Driver d: arrDriver) {
            Drivers.driverNamesAll.add(d.getName());
        }

        ObservableList<String> drivers = FXCollections.observableArrayList(Drivers.driverNamesAll);
        selectDriver.setItems(drivers);



        // adding vehicles
        ArrayList<Vehicle> arrVehicles = Vehicles.vehicleList;
        arrVehicles.add(new Vehicle("NA-3434",3500.00,60,"Bus",true));
        arrVehicles.add(new Vehicle("KA-4563",1000.00,7,"Van",true));
        arrVehicles.add(new Vehicle("58-3567",1500.00,4,"Van",true));
        arrVehicles.add(new Vehicle("GF-4358",800.00,4,"Van",true));
        arrVehicles.add(new Vehicle("CCB-3568",1800.00,8,"Van",true));
        arrVehicles.add(new Vehicle("LM-6679",1500.00,4,"Van",true));
        arrVehicles.add(new Vehicle("QA-3369",1800.00,6,"Van",true));
        arrVehicles.add(new Vehicle("KB-3668",2500.00,2,"Cargo Lorry",true));
        arrVehicles.add(new Vehicle("JJ-9878",3000.00,2,"Cargo Lorry",true));
        arrVehicles.add(new Vehicle("GH-5772",4000.00,3,"Cargo Lorry",true));
        arrVehicles.add(new Vehicle("XY-4456",3500.00,2,"Cargo Lorry",true));
        arrVehicles.add(new Vehicle("YQ-3536",2000.00,2,"Cargo Lorry",true));
        arrVehicles.add(new Vehicle("CBB-3566",2500.00,2,"Cargo Lorry",true));
        arrVehicles.add(new Vehicle("QH-3444",5000.00,4,"Cargo Lorry",true));

        // updating combo-box
        for (Vehicle v: arrVehicles) {
            Vehicles.vehicleNumList.add(v.getvNumber());
        }

        ObservableList<String> vehicles = FXCollections.observableArrayList(Vehicles.vehicleNumList);
        selectVehicle.setItems(vehicles);

    }

    private void addingParkingSlots(){
        parkingNumber.setText(null);
        for(int i =1;i<=14;i++){
            ParkingSlots.slots.add(new ParkingSlot(i));
        }
        ParkingSlots.vanSlots.add(1);
        ParkingSlots.vanSlots.add(2);
        ParkingSlots.vanSlots.add(3);
        ParkingSlots.vanSlots.add(4);
        ParkingSlots.vanSlots.add(12);
        ParkingSlots.vanSlots.add(13);
        ParkingSlots.lorrySlots.add(5);
        ParkingSlots.lorrySlots.add(6);
        ParkingSlots.lorrySlots.add(7);
        ParkingSlots.lorrySlots.add(8);
        ParkingSlots.lorrySlots.add(9);
        ParkingSlots.lorrySlots.add(10);
        ParkingSlots.lorrySlots.add(11);
    }

    private void addingVehiclesToParkingSlots(){
        int i,j;
        i=j=0;
        // adding bus (14)
        Vehicles.vehicleList.get(0).setParkingSlot(14);
        // adding vans (1,2,3,4,12,13) and lorries (5,6,7,8,9,10,11)
        for (Vehicle v:
        Vehicles.vehicleList) {
            if(v.getVehicleType().equals("Van")){
                v.setParkingSlot(ParkingSlots.vanSlots.get(i));
                i++;
            }else if(v.getVehicleType().equals("Cargo Lorry")){
                v.setParkingSlot(ParkingSlots.lorrySlots.get(j));
                j++;
            }
        }

    }

    // calculate real-time date and time.....
    private String[] calDateAndTime(){
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        String[] dateAndTime = formattedDate.split(" ");
        return dateAndTime;
    }

    // managing vehicles before loading the app
    private void managingVehiclesOnStart(){
        // getting the date before one day
        LocalDate today = LocalDate.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String prevDay =  today.minusDays(1).format(myFormatObj).toString();
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++
        ArrayList<inParkingVehicleManager> mng = VehicleManager.inPark;
        for (Vehicle v:
             Vehicles.vehicleList) {
            mng.add(new inParkingVehicleManager(v.getvNumber(),v.getVehicleType(),v.getParkingSlot(),prevDay + "-" + txtTime.getText()));
        }
    }

    private void inParkingVehicleManagement(Vehicle v){
        // removing from delivery manager db
        VehicleManager.onDelivery.removeIf(d -> v.getvNumber().equals(d.getvNumber()));
        // adding to the parking manager db
        String dateAndTime = txtDate.getText().toString() + "-"  + txtTime.getText().toString();
        VehicleManager.inPark.add(new inParkingVehicleManager(v.getvNumber(),v.getVehicleType(),v.getParkingSlot(),dateAndTime));
    }

    private void onDeliveryVehicleManagement(Vehicle v){
        // removing from parking manager db
        VehicleManager.inPark.removeIf(p -> v.getvNumber().equals(p.getvNumber()));
        // adding to the delivery manager db
        String dateAndTime = txtDate.getText().toString() + "-"  + txtTime.getText().toString();
        VehicleManager.onDelivery.add(new onDeliveryVehicleManager(v.getvNumber(),v.getVehicleType(),v.getDriver(),dateAndTime));
    }

    public void parkVehicleOnAction(ActionEvent actionEvent) {
        boolean continuee = exceptionHandling();
        if(freeSlots>0 & continuee){
            freeSlots--;
            // customizing vehicle object
            String val = selectVehicle.getValue().toString();
            Vehicle vehicle = null;

            for (Vehicle v:
                    Vehicles.vehicleList) {
                if(v.getvNumber().equals(val)){
                    v.setParked(true);
                    v.setDriver(null);

                    // binding the slot with the vehicle
                    v.setParkingSlot(Integer.parseInt(parkingNumber.getText()));
                    vehicle = v;

                }
            }

            // customizing driver object
            updateDriverDbOnAction(false);

            // making the slot reserved
            int num = Integer.parseInt(parkingNumber.getText());
            ParkingSlot found = null;
            for (ParkingSlot p:
                    ParkingSlots.slots) {
                if(p.getSlotNum()==num){
                    found = p;
                }
            }
            found.setFree(false);

            // parking management
            inParkingVehicleManagement(vehicle);

            // refreshing the dashboard
            refresh();
        }else if(freeSlots==0){
            new Alert(Alert.AlertType.WARNING,"Not enough parking slots available!").show();
            refresh();
        }


    }

    public void onDeliveryShiftOnAction(ActionEvent actionEvent) {
        boolean continuee = exceptionHandling();
        if(continuee){
            freeSlots++;
            // customizing vehicle object
            String val = selectVehicle.getValue().toString();
            Vehicle vehicle = null;
            for (Vehicle v:
                    Vehicles.vehicleList) {
                if(v.getvNumber().equals(val)){
                    v.setParked(false);
                    v.setDriver(selectDriver.getValue().toString());
                    // removing the slot from the vehicle
                    v.setParkingSlot(-1);
                    vehicle = v;
                }
            }

            // customizing driver object
            updateDriverDbOnAction(true);

            // making the slot empty
            int num = Integer.parseInt(parkingNumber.getText());
            ParkingSlot found = null;
            for (ParkingSlot p:
                    ParkingSlots.slots) {
                if(p.getSlotNum()==num){
                    found = p;
                }
            }
            found.setFree(true);

            // managing delivery
            onDeliveryVehicleManagement(vehicle);

            // refreshing dashboard
            refresh();
        }
    }

    public void managementLogInOnAction(ActionEvent actionEvent) throws IOException {
        loginStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/managementLogIn.fxml"))));
        loginStage.setTitle("Management-LogIn");
        loginStage.show();
    }

    private void updateDriverDbOnAction(boolean shift) {
        String driverName = selectDriver.getValue().toString();

        // setting shift
        for (Driver d:
             Drivers.driverList) {
            if(d.getName().equals(driverName)){
                d.setOnShift(shift);
            }
        }

        // updating combo-box
        ArrayList<Driver> tempDrivers = new ArrayList<>();
        for (Driver d:
             Drivers.driverList) {
            if(!d.isOnShift()){
                tempDrivers.add(d);
            }
        }
        Drivers.driverNamesOnFree.clear();
        for (Driver d:tempDrivers) {
            if(!d.isOnShift()){
                Drivers.driverNamesOnFree.add(d.getName());
            }
        }
        ObservableList<String> drivers = FXCollections.observableArrayList(Drivers.driverNamesOnFree);
        selectDriver.setItems(drivers);


    }

    public void updateVehicleTypeOnAction(ActionEvent actionEvent)  {

        String vehicleNum = null;
        if(!(selectVehicle.getValue()==null | selectVehicle.getValue().toString()=="")){
            vehicleNum = selectVehicle.getValue().toString();
        }

        if(!(vehicleNum==null)){
            for (Vehicle v:
                    Vehicles.vehicleList){
                if(vehicleNum.equals(v.getvNumber())){

                    txtVehicleType.setText(v.getVehicleType());

                    // disabling buttons
                    if(v.isParked()){
                        parkbtn.setDisable(true);
                        deliverybtn.setDisable(false);
                        selectDriver.setDisable(false);
                        parkingNumber.setText(Integer.toString(v.getParkingSlot()));
                    }else{

                        // adding the driver on shift with the vehicle
                        selectDriver.setValue(v.getDriver());
                        selectDriver.setDisable(true);

                        // showing a slot to park the vehicle
                        int slot = 0;
                        if(v.getVehicleType().equals("Bus")){
                            slot=ParkingSlots.slots.get(13).getSlotNum();
                        }
                        else if(v.getVehicleType().equals("Van")){
                            outer : for (ParkingSlot p:
                                    ParkingSlots.slots){
                                if(p.isFree()){
                                    for(int n:
                                            ParkingSlots.vanSlots) {
                                        if(p.getSlotNum()==n){
                                            slot=n;
                                            break outer;
                                        }
                                    }
                                }
                            }
                        }
                        else{
                            outer : for (ParkingSlot p:
                                    ParkingSlots.slots) {
                                if(p.isFree()){
                                    for (int n:
                                            ParkingSlots.lorrySlots) {
                                        if(p.getSlotNum()==n){
                                            slot=n;
                                            break outer;
                                        }
                                    }
                                }
                            }
                        }

                        // setting the slot to the text
                        parkingNumber.setText(Integer.toString(slot));

                        deliverybtn.setDisable(true);
                        parkbtn.setDisable(false);
                    }

                };
            }
        }

    }

    public void refresh(){
        parkbtn.setDisable(false);
        deliverybtn.setDisable(false);
        selectDriver.setValue("");
        selectVehicle.setValue("");
        txtVehicleType.setText(null);
        parkingNumber.setText(null);
    }

    public void onMouseClicked(MouseEvent mouseEvent) {
        selectDriver.setValue("");
    }

    public void refreshAll(ActionEvent actionEvent) {
        if(newDrivers>0 | newVehicles>0){
            // updating vehicle combo
            ObservableList<String> vehicles = FXCollections.observableArrayList(Vehicles.vehicleNumList);
            selectVehicle.setItems(vehicles);

            // updating driver combo
            selectDriver.getItems().clear();
            ArrayList<String> names = Drivers.driverNamesOnFree;
            names.clear();
            for (Driver d:
                 Drivers.driverList) {
                if(!d.isOnShift()){
                    names.add(d.getName());
                }
            }
            ObservableList<String> drivers = FXCollections.observableArrayList(Drivers.driverNamesOnFree);
            selectDriver.setItems(drivers);
        }
    }

    public boolean exceptionHandling(){
        if(selectDriver.getValue()==null & selectVehicle.getValue()==null){
            new Alert(Alert.AlertType.WARNING,"All input fields must be filled!\nPlease check before submitting").show();
            //refresh();
            return false;
        }
        else if(selectDriver.getValue().toString()=="" & selectVehicle.getValue().toString()==""){
            new Alert(Alert.AlertType.WARNING,"All input fields must be filled!\nPlease check before submitting").show();
            //refresh();
            return false;
        }
        else if(selectDriver.getValue().toString()=="" | selectDriver.getValue()==null){
            new Alert(Alert.AlertType.WARNING,"A Driver must be selected!\nPlease check before submitting").show();
            //refresh();
            return false;
        }
        else if(selectVehicle.getValue().toString()=="" | selectVehicle.getValue()==null){
            new Alert(Alert.AlertType.WARNING,"A Vehicle must be selected!\nPlease check before submitting").show();
            //refresh();
            return false;
        }
        else{
            return true;
        }
    }

}
