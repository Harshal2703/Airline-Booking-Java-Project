package com.example.airlinesbooking;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class testMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setScene(new Scene(new FXMLLoader(testMain.class.getResource("login.fxml")).load()));
        primaryStage.setResizable(false);
        Image icon = new Image("E:\\Mini-Project\\AirlinesBooking\\src\\main\\resources\\com\\example\\airlinesbooking\\Icons\\globe.png");
        primaryStage.getIcons().add(icon);
        primaryStage.show();
    }
}
