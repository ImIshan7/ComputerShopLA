package lk.ijse.computershop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.computershop.db.DBConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Objects;


public class IncomeController {

    @FXML
    private AnchorPane IncomeID;

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {

        Stage stage = (Stage) IncomeID.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/DashBoard.fxml")))));
    }



    @FXML
    void btnCustomerOnAction(ActionEvent event) {
        InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/computershop/report/Customer.jrxml");

       try{
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (SQLException | ClassNotFoundException | JRException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {


    }

    @FXML
    void btnProductOnAction(ActionEvent event) {

        InputStream resource = this.getClass().getResourceAsStream("lk/ijse/computershop/report/Product.jrxml");



        try{
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (SQLException | ClassNotFoundException | JRException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) {
        InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/computershop/report/Supplier.jrxml");


        try{
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
        } catch (SQLException | ClassNotFoundException | JRException e) {
            e.printStackTrace();
        }

    }




}
