package lk.ijse.computershop.controller;

import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.computershop.bo.BOFactory;
import lk.ijse.computershop.bo.custom.SupplierBO;
import lk.ijse.computershop.db.DBConnection;
import lk.ijse.computershop.dto.SupplierDTO;
import lk.ijse.computershop.view.tm.CustomerTm;
import lk.ijse.computershop.view.tm.SupplierTm;


import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SupplierController {

    public JFXTextArea txtSearchID;
    public TableView<SupplierTm>tblSupplier;
    public TableColumn ColSupID;
    public TableColumn ColName;
    public TableColumn ColAddress;

    public JFXTextArea txtBrand;
    public Label txtModel1;
    public JFXTextArea txtPrice;
    public TableColumn ColBrand;
    public TableColumn ColPrice;
    public JFXTextArea txtQTY;
    public TableColumn ColQTY;
    public Label txtModel11;
    public Label lblSupID;
    public Label lblName;
    public Label lblAddress;
    public Label lblBrand;
    public Label lblUnitePrice;
    public Label lblContact;
    @FXML
    private AnchorPane SupplierID;

    @FXML
    private JFXTextArea txtAddress;

    @FXML
    private JFXTextArea txtID;

    @FXML
    private Label txtModel;

    @FXML
    private JFXTextArea txtName;

    private String searchText="";
    String selectedID;

    private Matcher SupIDMatcher;
    private Matcher SupNameMatcher;
    private Matcher SupAddressMatcher;
    private Matcher SupBrandMatcher;
    private Matcher SupUnitPriceMatcher;
    private Matcher SupQTYMatcher;


SupplierBO supplierBO = (SupplierBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.SUPPLIER);

    public static ObservableList obList = FXCollections.observableArrayList();


    public void initialize() throws SQLException, ClassNotFoundException {

        obList.clear();

        ColSupID.setCellValueFactory(new PropertyValueFactory<>("supid"));
        ColName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        ColBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        ColPrice.setCellValueFactory(new PropertyValueFactory<>("unit_Price"));
        ColQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));


        setPattern();

        ArrayList arrayList = supplierBO.getAllSupplier();

        for (Object e : arrayList){
            obList.add(e);
        }

        searchPart();

        tblSupplier.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectedID = newValue.getSupid();

                System.out.println(newValue.getSupid());

                txtID.setText(newValue.getSupid());
                txtName.setText(newValue.getName());
                txtAddress.setText(newValue.getAddress());
                txtBrand.setText(newValue.getBrand());
                txtPrice.setText(String.valueOf(newValue.getUnit_Price()));
                txtQTY.setText(String.valueOf(newValue.getQty()));
            }
        } );
    }


   /* private void loadAllSupplier(){
        tblSupplier.getItems().clear();
        try {
            ArrayList<SupplierDTO> allSupplier = supplierBO.getAllSupplier();

            for (SupplierDTO c : allSupplier){
                tblSupplier.getItems().add(new SupplierTm(c.getSupid(),c.getName(),c.getAddress(),c.getBrand(),c.getUnit_Price(),c.getQty()));

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/



    public void setPattern() {

        Pattern IDMatcher = Pattern.compile("^(S0)([0-9]{1})([0-9]{1,})$");
        SupIDMatcher = IDMatcher.matcher(txtID.getText());

        Pattern NamePattern = Pattern.compile("^([a-zA-Z]{4,})$");
        SupNameMatcher = NamePattern.matcher(txtName.getText());

        Pattern AddressPattern = Pattern.compile("^([a-zA-Z]{4,})$");
        SupAddressMatcher = AddressPattern.matcher(txtAddress.getText());

        Pattern BrandPattern = Pattern.compile("^([a-zA-Z]{4,})$");
        SupBrandMatcher = BrandPattern.matcher(txtBrand.getText());

        Pattern UnitePricePattern = Pattern.compile("^[0-9]{1,}$");
        SupUnitPriceMatcher = UnitePricePattern.matcher(txtPrice.getText());

        Pattern QTYPattern = Pattern.compile("^[0-9]{1,}$");
        SupQTYMatcher = QTYPattern.matcher(txtQTY.getText());
        
    }


    @FXML
    void btnAddOnAction(ActionEvent event) {

        String SupID = txtID.getText();
        String Name = txtName.getText();
        String Address = txtAddress.getText();
        String Brand = txtBrand.getText();
        Double Unit_Price = Double.valueOf(txtPrice.getText());
        int QTY = Integer.parseInt(txtQTY.getText());



        setPattern();
        if (SupIDMatcher.matches()) {
            if (SupNameMatcher.matches()) {
                if (SupAddressMatcher.matches()) {
                    if (SupBrandMatcher.matches()) {
                        if (SupUnitPriceMatcher.matches()) {
                          if (SupQTYMatcher.matches()) {


                          }else {
                              txtQTY.requestFocus();
                              lblContact.setText("Invalied QTY");
                          }
                        }  else{
                            txtPrice.requestFocus();
                            lblUnitePrice.setText("Invalied Price");
                        }

                    } else {
                        txtBrand.requestFocus();
                        lblBrand.setText("invalid Brand ");
                    }
                } else {
                    txtAddress.requestFocus();
                    lblAddress.setText("invalid Address ");
                }
            } else {
                txtName.requestFocus();
                lblName.setText("invalid Name");
            }

        } else {
            txtID.requestFocus();
            lblSupID.setText("invalid SupID ");

        }


        try {
            boolean isAdded = supplierBO.addSupplier(new SupplierDTO(SupID,Name,Address,Brand,Unit_Price,QTY));


            if (isAdded) {

                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Added  Successfully!").show();

                cleardata();
                initialize();

            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }





    private void cleardata(){
        txtID.clear();
        txtName.clear();
        txtAddress.clear();
        txtBrand.clear();
        txtPrice.clear();
        txtQTY.clear();
    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        Alert alert = new Alert(Alert.AlertType.WARNING, "Are You Sure You Want To Delete These Supplier?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            String SupID = tblSupplier.getSelectionModel().getSelectedItem().getSupid();
            boolean isDeleted = supplierBO.deleteSupplier(SupID);

            if (isDeleted) {


                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Deleted!").show();

                cleardata();
                initialize();

            } else new Alert(Alert.AlertType.WARNING, "Something happened!").show();
        }
    }

    private void searchPart() {
        // search customer
        FilteredList<SupplierTm> filteredList = new FilteredList(obList, b -> true);

        txtSearchID.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(supplierTm -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (supplierTm.getSupid().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (supplierTm.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(supplierTm.getAddress()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(supplierTm.getBrand()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(supplierTm.getUnit_Price()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(supplierTm.getQty()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<SupplierTm> sortedList = new SortedList(filteredList);
        sortedList.comparatorProperty().bind(tblSupplier.comparatorProperty());
        tblSupplier.setItems(sortedList);
    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {


        String SupID = txtID.getText();
        String Name = txtName.getText();
        String Address = txtAddress.getText();
        String Brand = txtBrand.getText();
        Double Unit_Price = Double.valueOf(txtPrice.getText());
        int QTY = Integer.parseInt(txtQTY.getText());


        boolean isUpdate = supplierBO.updateSupplier(new SupplierDTO(SupID,Name,Address,Brand,Unit_Price,QTY));


        if (isUpdate) {

            cleardata();

            new Alert(Alert.AlertType.CONFIRMATION, "Supplier Update Successfully!").show();

            initialize();

        } else {
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
        }


    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)SupplierID.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/DashBoard.fxml")))));
    }


    public void txtSupIDKeyTypeOnAction(KeyEvent keyEvent) {

        lblSupID.setText("");

        Pattern IDMatcher = Pattern.compile("^(S0)([0-9]{1})([0-9]{1,})$");
        SupIDMatcher = IDMatcher.matcher(txtID.getText());

        if (!SupIDMatcher.matches()) {
            txtID.requestFocus();
            lblSupID.setText("invalid ID");
        }
    }

    public void txtNameKeyTypeOnAction(KeyEvent keyEvent) {
        lblName.setText("");

        Pattern NamePattern = Pattern.compile("^([a-zA-Z]{4,})$");
        SupNameMatcher = NamePattern.matcher(txtName.getText());

        if (!SupNameMatcher.matches()) {
            txtName.requestFocus();
            lblName.setText("invalid Name");
        }

    }

    public void txtAddressKeyTypeOnAction(KeyEvent keyEvent) {

        lblAddress.setText("");

        Pattern AddressPattern = Pattern.compile("^([a-zA-Z]{4,})$");
        SupAddressMatcher = AddressPattern.matcher(txtAddress.getText());

        if (!SupAddressMatcher.matches()) {
            txtAddress.requestFocus();
            lblAddress.setText("invalid Address");
        }

    }

    public void txtBrandKeyTypeOnAction(KeyEvent keyEvent) {

        lblBrand.setText("");

        Pattern BrandPattern = Pattern.compile("^([a-zA-Z]{4,})$");
        SupBrandMatcher = BrandPattern.matcher(txtBrand.getText());

        if (!SupBrandMatcher.matches()) {
            txtBrand.requestFocus();
            lblBrand.setText("invalid Brand");
        }
    }

    public void txtUnitPriceKeyOnAction(KeyEvent keyEvent) {

        lblUnitePrice.setText("");

        Pattern UnitePricePattern = Pattern.compile("^[0-9]{1,}$");
        SupUnitPriceMatcher = UnitePricePattern.matcher(txtPrice.getText());

        if (!SupUnitPriceMatcher.matches()) {
            txtPrice.requestFocus();
            lblUnitePrice.setText("invalid UnitPrice");
        }
    }

    public void txtQtyKeyTypeOnAction(KeyEvent keyEvent) {

        lblContact.setText("");

        Pattern QTYPattern = Pattern.compile("^[0-9]{1,}$");
        SupQTYMatcher = QTYPattern.matcher(txtQTY.getText());

        if (!SupQTYMatcher.matches()) {
            txtQTY.requestFocus();
            lblContact.setText("invalid QTY");
        }
    }
}
