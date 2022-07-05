import controllers.mainStage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;


public class Appinitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start (Stage primaryStage) throws IOException {
        try {
            mainStage.dashStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("views/dashboard.fxml"))));
            mainStage.dashStage.setTitle("Dashboard");
            mainStage.dashStage.show();
            mainStage.dashStage.setOnCloseRequest(e -> {
                System.exit(0);
            });
        }catch(LoadException e){
            System.out.println(e);
        }
    }
}
