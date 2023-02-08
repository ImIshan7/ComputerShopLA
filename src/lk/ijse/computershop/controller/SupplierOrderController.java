package lk.ijse.computershop.controller;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.computershop.model.SupOrdersModel;
import lk.ijse.computershop.model.SupPlaceOrderModel;
import lk.ijse.computershop.entity.*;
import lk.ijse.computershop.view.tm.SupCartTm;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;

public class SupplierOrderController {

        public Label lblSupOrderID;
        public Label lbldate;
        public Label lbltime;

        public JFXTextArea txtOrderQTYID;
        @FXML
        private TableColumn<?, ?> ColDESC;

        @FXML
        private TableColumn<?, ?> ColPrdID;

        @FXML
        private TableColumn<?, ?> ColQTY;

        @FXML
        private TableColumn<?, ?> ColSupID;

        @FXML
        private TableColumn<?, ?> ColTotal;

        @FXML
        private TableColumn<?, ?> ColUnitePrice;

        @FXML
        private AnchorPane SupplierOrderID;

        @FXML
        private JFXComboBox<String> cmbProductID;

        @FXML
        private JFXComboBox<String> cmbSupplerID;

        @FXML
        private Label lblTotal;

        @FXML
        private TableView<SupCartTm> tblSupOrderID;

        @FXML
        private JFXTextArea txtAddress;

        @FXML
        private JFXTextArea txtBrand;

        @FXML
        private JFXTextArea txtName;

        @FXML
        private JFXTextArea txtPDESCID;

        @FXML
        private JFXTextArea txtPNameID;

        @FXML
        private JFXTextArea txtPQTYID;

        @FXML
        private JFXTextArea txtPUnitPriceID;

        @FXML
        private JFXTextArea txtUnitPrice;

        public void initialize() throws SQLException, ClassNotFoundException {

                ColSupID.setCellValueFactory(new PropertyValueFactory<>("SupID"));
                ColPrdID.setCellValueFactory(new PropertyValueFactory<>("PrdID"));
                ColQTY.setCellValueFactory(new PropertyValueFactory<>("QTY"));
                ColDESC.setCellValueFactory(new PropertyValueFactory<>("Description"));
                ColUnitePrice.setCellValueFactory(new PropertyValueFactory<>("Unit_Price"));
                ColTotal.setCellValueFactory(new PropertyValueFactory<>("Total"));

                loadOrderDate();
                loadOrderTime();
                loadNextOrderId();
                loadAllSuppliers();
                loadAllProduct();


                cmbSupplerID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
                        if (newValue!=null){
                                setSuppliers();
                        }
                } );

                cmbProductID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
                        if (newValue!=null){
                                setProducts();
                        }
                } );

        }


        private void loadAllSuppliers() throws SQLException, ClassNotFoundException {

//                for (Supplier supplier:SupplierController.getAllSuppliers()){
//                        cmbSupplerID.getItems().add(supplier.getSupID());
//                }
        }
        private void loadAllProduct() throws SQLException, ClassNotFoundException {

//                for (Product product:ProductController.getAllProduct()){
//                        cmbProductID.getItems().add(product.getPrdID());
//                }

        }

        private void setSuppliers() {
//                try {
//                        for (Supplier supplier :SupplierController.getAllSuppliers()) {
//
//                                if (supplier.getSupID().equals(cmbSupplerID.getValue())) {
//                                        txtName.setText(supplier.getName());
//                                        txtAddress.setText(supplier.getAddress());
//                                        txtBrand.setText(supplier.getBrand());
//                                        txtUnitPrice.setText(String.valueOf(supplier.getUnit_Price()));
//                                }
//                        }
//
//
//                }catch(Exception e){
//
//                }
        }

        private void setProducts() {
//                try {
//                        for (Product product : ProductController.getAllProduct()) {
//
//                                if (product.getPrdID().equals(cmbProductID.getValue())) {
//                                        txtPNameID.setText(product.getName());
//                                        txtPDESCID.setText(product.getDescription());
//                                        txtPQTYID.setText(String.valueOf(product.getQTY()));
//                                }
//                        }
//
//
//                }catch(Exception e){
//
//                }
        }


       private void loadNextOrderId() throws SQLException, ClassNotFoundException {
               String oID = SupOrdersModel.generateNextOID();
               lblSupOrderID.setText(oID);
        }


        private void loadOrderDate() {
                lbldate.setText(String.valueOf(LocalDate.now()));
        }

        private void loadOrderTime() {
                lbltime.setText(String.valueOf(LocalTime.now()));
        }

        static ObservableList<SupCartTm> obList= FXCollections.observableArrayList();


        @FXML
        void btnAddOnAction(ActionEvent event) {
                String SupID = cmbSupplerID.getSelectionModel().getSelectedItem();
                String PrdID = cmbProductID.getSelectionModel().getSelectedItem();
                int qty= Integer.parseInt((txtOrderQTYID.getText()));
                String Description = txtPDESCID.getText();
                double Unit_Price= Double.parseDouble((txtUnitPrice.getText()));
                double Total=qty*Unit_Price;

                SupCartTm supCartTm = new SupCartTm(SupID,PrdID,qty,Description,Unit_Price,Total);


                for (int i = 0; i < tblSupOrderID.getItems().size(); i++) {
                        if (ColPrdID.getCellData(i).equals(String.valueOf(cmbProductID.getSelectionModel().getSelectedItem()))) {
                                int tempQty = obList.get(i).getQTY() + qty;

                                double tot = Unit_Price*tempQty;

                                obList.get(i).setQTY(tempQty);

                                obList.get(i).setTotal(tot);

                                tblSupOrderID.refresh();

                                generateTotal();

                                return;
                        }
                }

                obList.add(supCartTm);

                tblSupOrderID.setItems(obList);

                generateTotal();

                calculateTotal();
                cmbProductID.requestFocus();
        }

        public void generateTotal(){
                double total = 0;
                for (int i = 0; i < tblSupOrderID.getItems().size(); i++) {
                        total += Double.parseDouble(String.valueOf(ColTotal.getCellData(i)));

                }
                lblTotal.setText(String.valueOf(total));
        }






        private int isAlredyExists(String code) {
                for (int i = 0; i < obList.size(); i++) {
                        if (obList.get(i).getSupID().equals(code)) {
                                return i;
                        }
                }
                return -1;
        }

                private void calculateTotal(){
                        double total=0.00;
                        for (SupCartTm tm:obList
                        ) {
                                total+=tm.getTotal();
                        }
                        lblTotal.setText(String.valueOf(total));
                }


        @FXML
        void btnBackOnAction(ActionEvent event) throws IOException {
            Stage stage = (Stage) SupplierOrderID.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/DashBoard.fxml")))));

        }

        @FXML
        void btnOrderOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

                String SupOrderID = lblSupOrderID.getText();

                String SupID = cmbSupplerID.getValue();
                int Qty = Integer.parseInt(txtOrderQTYID.getText());
                String Desc = txtPDESCID.getText();
                double UnitPrice = Double.parseDouble(txtUnitPrice.getText());
                Date date = Date.valueOf(LocalDate.now());

                ArrayList<SupOrderDetail> supOrderDetail = new ArrayList<>();
                for (int i = 0; i < tblSupOrderID.getItems().size(); i++) {
                        String PrdID = String.valueOf(ColPrdID.getCellData(i));
                        double unitPrice = Double.parseDouble(String.valueOf(ColUnitePrice.getCellData(i)));
                        int QTY = Integer.parseInt(String.valueOf(ColQTY.getCellData(i)));

                        SupOrderDetail suporderDetail = new SupOrderDetail(PrdID,SupOrderID,Qty,Desc,UnitPrice);
                        supOrderDetail.add(suporderDetail);
                }
                SupOrders supOrders = new SupOrders(SupOrderID,SupID,date,supOrderDetail);

                boolean SupPlaceOrder = SupPlaceOrderModel.SupPlaceOrder(supOrders);

                if (SupPlaceOrder){
                        if (SupPlaceOrder) {
                                new Alert(Alert.AlertType.CONFIRMATION, "Order Succssefully!").show();
                        } else {
                                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
                        }
                }

        }

        @FXML
        void cmbProductOnAction(ActionEvent event) {

        }

        @FXML
        void cmbSupplierOnAction(ActionEvent event) {

        }

    }


