package lk.ijse.computershop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class RepAboutController {


    public AnchorPane AboutID;

    public void btnRepHomeOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) AboutID.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/RepDashBoard.fxml"))));


    }
}

