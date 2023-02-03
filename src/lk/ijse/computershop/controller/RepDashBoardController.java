package lk.ijse.computershop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.computershop.model.CustomerModel;
import lk.ijse.computershop.model.ProductModel;
import lk.ijse.computershop.model.ServiceModel;
import lk.ijse.computershop.util.Navigation;
import lk.ijse.computershop.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class RepDashBoardController {

    public Label lblCustomer;
    public Label lblProduct;
    public Label lblService;
    @FXML
    private AnchorPane RepDashBoardID;

    @FXML
    private AnchorPane RepDashID;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblday;

    public void initialize() throws SQLException, ClassNotFoundException {

        loadOrderDate();
        loadOrderTime();
        countCustomers();
        countProduct();
        countService();
    }

    private void countCustomers() throws SQLException, ClassNotFoundException {

        lblCustomer.setText(String.valueOf(CustomerModel.CustomerCount()));
    }

    private void countProduct() throws SQLException, ClassNotFoundException {
        lblProduct.setText(String.valueOf(ProductModel.ProductCount()));
    }

    private void countService() throws SQLException, ClassNotFoundException {
        lblService.setText(String.valueOf(ServiceModel.ServiceCount()));
    }



    private void loadOrderDate() {
        lblday.setText(String.valueOf(LocalDate.now()));
    }

    private void loadOrderTime() {
        lblTime.setText(String.valueOf(LocalTime.now()));
    }


    @FXML
    void btnAbout1OnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) RepDashBoardID.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/RepAbout.fxml"))));

    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) RepDashBoardID.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ManagerLogin.fxml"))));
    }

    @FXML
    void btnCustomer1OnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Routes.CUSTOMER,RepDashBoardID);

    }

    @FXML
    void btnEmploy1OnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Routes.EMPLOY,RepDashBoardID);

    }


    @FXML
    void btnService1OnAction(ActionEvent event) throws IOException {
        Navigation.navigation(Routes.SERVICE,RepDashBoardID);

    }


    public void btnLogoutOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }

}

