package lk.ijse.computershop.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.computershop.util.Navigation;
import lk.ijse.computershop.util.Routes;

import java.io.IOException;

public class ManagerLoginController {

    private static int status;
    public AnchorPane ManagerLogin;
    public PasswordField txtUserName;
    public PasswordField txtPassword;
    public AnchorPane LoginID;
    public AnchorPane LoginIDA;
    public JFXButton signID;



    public void btnSignOnAction(ActionEvent actionEvent) throws IOException {
        if (txtUserName.getText().equals("aaa") && txtPassword.getText().equals("aaa")) {
            status = 1;
            Navigation.navigation(Routes.DASHBOARD, ManagerLogin);
        } else if (txtUserName.getText().equals("qqq") && txtPassword.getText().equals("qqq")){
            status = 2;
            Navigation.navigation(Routes.REPDASHBOARD,ManagerLogin);
        } else {
            new Alert(Alert.AlertType.WARNING, "Incorrect username or password!").show();
        }

    }



    public void btnUserOnAction(ActionEvent actionEvent) {
    }

    public void btnPasswordOnAction(ActionEvent actionEvent) {
    }

    public void btnExistOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }
}


