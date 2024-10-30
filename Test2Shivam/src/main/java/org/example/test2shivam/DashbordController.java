package org.example.test2shivam;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class DashbordController {


    public Label demo;

    public void logoutclick(ActionEvent actionEvent) {

        try {
            Parent secondScene = FXMLLoader.load(getClass().getResource("hello-view.fxml"));

            Stage secondStage = new Stage();
            secondStage.setTitle("Login");
            secondStage.setScene(new Scene(secondScene));
            Stage firstSceneStage = (Stage) demo.getScene().getWindow();

            firstSceneStage.close();


            secondStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void crudclick(ActionEvent actionEvent) {

        try {
            Parent secondScene = FXMLLoader.load(getClass().getResource("crud.fxml"));

            Stage secondStage = new Stage();
            secondStage.setTitle("crud");
            secondStage.setScene(new Scene(secondScene));
            Stage firstSceneStage = (Stage) demo.getScene().getWindow();

            firstSceneStage.close();


            secondStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}