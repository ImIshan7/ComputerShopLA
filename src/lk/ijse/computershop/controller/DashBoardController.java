package lk.ijse.computershop.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.computershop.model.CustomerModel;
import lk.ijse.computershop.model.OrderDetailModel;
import lk.ijse.computershop.model.ProductModel;
import lk.ijse.computershop.model.ServiceModel;
import lk.ijse.computershop.util.Navigation;
import lk.ijse.computershop.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class DashBoardController {
    public AnchorPane DashBoard;
    public AnchorPane DashBoardID;
    public JFXButton incomeID;
    public JFXButton SupplierID;
    public JFXButton ProductID;


    public JFXButton SupplierOrderID;
    public Label lblTime;
    public Label lblDate;
    public Label lblCustomer;
    public Label lblProduct;
    public Label lblSales;
    public Label lblService;

    public void initialize() throws SQLException, ClassNotFoundException {

        loadOrderDate();
        loadOrderTime();
        countCustomers();
        countProduct();
        countService();
        countOrders();

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

    private void countOrders() throws SQLException, ClassNotFoundException {
        lblSales.setText(String.valueOf(OrderDetailModel.OrdersCount()));
    }


    private void loadOrderDate() {
        lblDate.setText(String.valueOf(LocalDate.now()));
    }

    private void loadOrderTime() {
        lblTime.setText(String.valueOf(LocalTime.now()));
    }




    public void btnCustomerOnAction(ActionEvent actionEvent) throws IOException {
        //Stage stage = (Stage) DashBoardID.getScene().getWindow();
        //stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/Customer.fxml"))));
        Navigation.navigation(Routes.CUSTOMER,DashBoardID);
    }

    public void btnEmployOnAction(ActionEvent actionEvent) throws IOException {
      //  Stage stage = (Stage) DashBoard.getScene().getWindow();
       // stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/Employ.fxml"))));
        Navigation.navigation(Routes.EMPLOY,DashBoardID);
    }

    public void btnSupplierOnAction(ActionEvent actionEvent) throws IOException {
      //  Stage stage = (Stage) DashBoard.getScene().getWindow();
       // stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/Supplier.fxml"))));
        Navigation.navigation(Routes.SUPPLIER,DashBoardID);
    }

    public void btnProductOnAction(ActionEvent actionEvent) throws IOException {
       // Stage stage = (Stage) DashBoard.getScene().getWindow();
        //stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/Product.fxml"))));
        Navigation.navigation(Routes.PRODUCT,DashBoardID);
    }

    public void btnOrderPlaceOnAction(ActionEvent actionEvent) throws IOException {
       // Stage stage = (Stage) DashBoard.getScene().getWindow();
       // stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/OrderPlace.fxml"))));
        Navigation.navigation(Routes.ORDERPLACE,DashBoardID);
    }

    public void btnOrderOnAction(ActionEvent actionEvent) throws IOException {
            Navigation.navigation(Routes.ORDER,DashBoardID);

    }

    public void btnSupOrderOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routes.SUPPLIERORDER,DashBoardID);

    }
    public void btnServiceOnAction(ActionEvent actionEvent) throws IOException {
      //  Stage stage = (Stage) DashBoard.getScene().getWindow();
       // stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/Service.fxml"))));
        Navigation.navigation(Routes.SERVICE,DashBoardID);
    }

    public void btnIncomeOnAction(ActionEvent actionEvent) throws IOException {
      //  Stage stage = (Stage) DashBoard.getScene().getWindow();
       // stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/Income.fxml"))));
        Navigation.navigation(Routes.INCOME,DashBoardID);
    }



    public void btnAboutOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) DashBoard.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/About.fxml"))));
    }

    public void btnLogoutOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void btnBack0OnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) DashBoard.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ManagerLogin.fxml"))));
    }
}
