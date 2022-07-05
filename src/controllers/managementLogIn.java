package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.event.WindowEvent;
import java.io.IOException;

public class managementLogIn {
    public TextField txtUsername;
    public TextField txtPwd;
    public Button loginBtn;
    public Button cancelBtn;
    public AnchorPane anchor;
    public static Stage shiftDetailsStage = new Stage();

    public void initialize(){
        loginBtn.setDefaultButton(true);
        cancelBtn.setCancelButton(true);
        managementLogIn.shiftDetailsStage.setOnCloseRequest(e -> {
            mainStage.dashStage.show();
        });
    }

    public void logInAction(ActionEvent actionEvent) throws IOException {
        if(txtUsername.getText().equals("admin") & txtPwd.getText().equals("saman1234")){
            Dashboard.loginStage.close();
            mainStage.dashStage.hide();
            shiftDetailsStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/shiftDetails.fxml"))));
            shiftDetailsStage.setTitle("Vehicle Management");
            shiftDetailsStage.show();
        }
        else{
            txtPwd.setText(null);
            txtUsername.setText(null);
            Alert alert = new Alert(Alert.AlertType.ERROR,"Sorry! Something went wrong");
            alert.show();
        }
    }

    public void cancelLogInOnAction(ActionEvent actionEvent) {
        Dashboard.loginStage.close();
    }

}
