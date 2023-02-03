package lk.ijse.computershop.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class Navigation {

    private static AnchorPane DashBoard;

    public static void navigation(Routes routes, AnchorPane DashBoard) throws IOException {
        Navigation.DashBoard = DashBoard;
        Navigation.DashBoard.getChildren().clear();
        Stage window = (Stage) Navigation.DashBoard.getScene().getWindow();

        switch (routes) {
            case DASHBOARD:
                window.setTitle("DASHBOARD");
                initUI("DashBoard.fxml");
                break;
        }

        switch (routes) {
            case REPDASHBOARD:
                window.setTitle("REPDASHBOARD");
                initUI("RepDashBoard.fxml");
                break;
        }

        switch (routes) {
            case CUSTOMER:
                window.setTitle("Customer");
                initUI("Customer.fxml");
                break;
        }

            switch (routes) {
                case EMPLOY:
                    window.setTitle("Employ");
                    initUI("Employ.fxml");
            }
             switch (routes) {
                 case SUPPLIER:
                     window.setTitle("SUPPLIER");
                     initUI("Supplier.fxml");
                     break;
            }

             switch (routes) {
                 case SUPPLIERORDER:
                     window.setTitle("SUPPLIERORDER");
                     initUI("SupplierOrder.fxml");
                     break;
             }

             switch (routes) {
                 case PRODUCT:
                     window.setTitle("PRODUCT");
                     initUI("Product.fxml");
                     break;
            }

            switch (routes) {
                case ORDERPLACE:
                    window.setTitle("ORDERPLACE");
                    initUI("OrderPlace.fxml");
                    break;
           }

        switch (routes) {
            case ORDER:
                window.setTitle("ORDER");
                initUI("Orders.fxml");
                break;
        }

            switch (routes) {
                case SERVICE:
                    window.setTitle("SERVICE");
                    initUI("Service.fxml");
                    break;
          }

            switch (routes) {
                case INCOME:
                     window.setTitle("INCOME");
                     initUI("Income.fxml");
                      break;
          }

    }

    private static void initUI(String location) throws IOException {
        Navigation.DashBoard.getChildren().add(FXMLLoader.load(Navigation.class.getResource("/lk/ijse/computershop/view/" + location)));
    }
}