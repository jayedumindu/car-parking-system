package controllers;

import javafx.event.ActionEvent;

abstract class CrudUtility {
    abstract void addDriverOnAction(ActionEvent actionEvent);
    abstract void addVehicleOnAction(ActionEvent actionEvent);
    abstract void refresh();
}
