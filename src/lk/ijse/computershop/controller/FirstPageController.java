package lk.ijse.computershop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class FirstPageController {
    public AnchorPane First;
    public static int status;



    public void btnManagerOnAction(ActionEvent actionEvent) throws IOException {
        status=1;
        Stage stage = (Stage) First.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ManagerLogin.fxml"))));
    }

    public void btnReceptionOnClick(ActionEvent actionEvent) throws IOException {
        status=2;
        Stage stage = (Stage) First.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ManagerLogin.fxml"))));
    }
    }

