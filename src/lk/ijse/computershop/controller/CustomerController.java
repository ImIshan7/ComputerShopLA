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
import lk.ijse.computershop.bo.custom.CustomerBO;
import lk.ijse.computershop.db.DBConnection;
import lk.ijse.computershop.dto.CustomerDTO;
import lk.ijse.computershop.view.tm.CustomerTm;


import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CustomerController {

        public TableView <CustomerTm>tblCustomer;
        public TableColumn ColID;
        public TableColumn ColName;
        public TableColumn ColAddress;
        public TableColumn ColContact;

    public JFXTextArea txtSearch;
    public Label lblCusID;
    public Label lblName;
    public Label lblAddress;
    public Label lblContact;
    @FXML
        private AnchorPane IshanID;

        @FXML
        private JFXTextArea txtAddress;

        @FXML
        private JFXTextArea txtContact;

        @FXML
        private JFXTextArea txtID;

        @FXML
        private JFXTextArea txtName;

    private String searchText="";
    String selectedID;

    private Matcher CusIDMatcher;
    private Matcher CusNameMatcher;
    private Matcher CusAddressMatcher;
    private Matcher CusContactMatcher;

   CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.CUSTOMER);

   public static ObservableList obList = FXCollections.observableArrayList();


    public void initialize() throws SQLException, ClassNotFoundException {

    obList.clear();

        ColID.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        ColAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        ColContact.setCellValueFactory(new PropertyValueFactory<>("Contact"));


        ArrayList arrayList = customerBO.getAllCustomers();

        for (Object e : arrayList){
            obList.add(e);
        }


        setPattern();

        searchPart();

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectedID = newValue.getId();

                System.out.println(newValue.getId());

                txtID.setText(newValue.getId());
                txtName.setText(newValue.getName());
                txtAddress.setText(newValue.getAddress());
                txtContact.setText(newValue.getContact());
            }
        });

        setPattern();

        }

    public void setPattern() {

        Pattern IDMatcher = Pattern.compile("^(C0)([0-9]{1})([0-9]{1,})$");
        CusIDMatcher = IDMatcher.matcher(txtID.getText());

        Pattern NamePattern = Pattern.compile("^([a-zA-Z]{4,})$");
        CusNameMatcher = NamePattern.matcher(txtName.getText());

        Pattern AddressPattern = Pattern.compile("^([a-zA-Z]{4,})$");
        CusAddressMatcher = AddressPattern.matcher(txtAddress.getText());

        Pattern ContactPattern = Pattern.compile("^(?:7|0|(?:\\+94))[0-9]{9,10}$");
        CusContactMatcher = ContactPattern.matcher(txtContact.getText());
    }



    private  void cleardata(){
        txtID.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
    }



    @FXML
    void btnRegOnAction (ActionEvent event) throws SQLException, ClassNotFoundException {
        String ID = txtID.getText();
        String Name = txtName.getText();
        String Address = txtAddress.getText();
        String Contact = txtContact.getText();

       // Customer customer = new Customer(ID, Name, Address, Contact);

     //   setPattern();
        if (CusIDMatcher.matches()) {
            if (CusNameMatcher.matches()) {
                if (CusAddressMatcher.matches()) {
                    if (CusContactMatcher.matches()) {


                    } else {
                        txtContact.requestFocus();
                        lblContact.setText("invalid Contact ");
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
            lblCusID.setText("invalid ID ");

        }

        try {
            boolean isAdded = customerBO.addCustomer(new CustomerDTO(txtID.getText(),txtName.getText(),txtAddress.getText(),txtContact.getText()));


          // AddTable(searchText);
//            searchPart();
//            cleardata();

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Register Successfully!").show();
                cleardata();
                initialize();
                //  AddTable(searchText);
//                loadAllCustomers();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }





    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


      boolean isUpdate =  customerBO.updateCustomer(new CustomerDTO(txtID.getText(),txtName.getText(),txtAddress.getText(),txtContact.getText()));

            if (isUpdate) {

                cleardata();
                txtSearch.clear();

                new Alert(Alert.AlertType.CONFIRMATION, "Customer Update Successfully!").show();

                cleardata();
                initialize();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }

        }

    private void searchPart() {
        // search customer
        FilteredList<CustomerTm> filteredList = new FilteredList(obList, b -> true);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(customerTm -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (customerTm.getId().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (customerTm.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(customerTm.getAddress()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(customerTm.getContact()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<CustomerTm> sortedList = new SortedList(filteredList);
        sortedList.comparatorProperty().bind(tblCustomer.comparatorProperty());
        tblCustomer.setItems(sortedList);
    }


    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Alert alert = new Alert(Alert.AlertType.WARNING, "Are You Sure You Want To Delete These Customer ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();

        String CusID = tblCustomer.getSelectionModel().getSelectedItem().getId();

               boolean isDeleted = customerBO.deleteCustomer(CusID);

            if (isDeleted) {

                new Alert(Alert.AlertType.CONFIRMATION, "Customer Deleted!").show();

                cleardata();
                initialize();

            } else new Alert(Alert.AlertType.WARNING, "Something happened!").show();
        }




    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) IshanID.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/DashBoard.fxml")))));
    }

        public void txtCusIDKeyTypeOnAction(KeyEvent keyEvent) {
        lblCusID.setText("");

        Pattern IDMatcher = Pattern.compile("^(C0)([0-9]{1})([0-9]{1,})$");
        CusIDMatcher = IDMatcher.matcher(txtID.getText());

        if (!CusIDMatcher.matches()) {
            txtID.requestFocus();
            lblCusID.setText("invalid ID");
        }
    }

    public void txtNameKeyTypeOnAction(KeyEvent keyEvent) {
        lblName.setText("");

        Pattern NamePattern = Pattern.compile("^([a-zA-Z]{4,})$");
        CusNameMatcher = NamePattern.matcher(txtName.getText());

        if (!CusNameMatcher.matches()) {
            txtName.requestFocus();
            lblName.setText("invalid Name");
        }
    }

    public void txtAddressKeyTypeOnAction(KeyEvent keyEvent) {
        lblAddress.setText("");

        Pattern AddressPattern = Pattern.compile("^([a-zA-Z]{4,})$");
        CusAddressMatcher = AddressPattern.matcher(txtAddress.getText());

        if (!CusAddressMatcher.matches()) {
            txtAddress.requestFocus();
            lblAddress.setText("invalid Address");
        }

    }

    public void txtContactKeyTypeOnAction(KeyEvent keyEvent) {
        lblContact.setText("");

        Pattern ContactPattern = Pattern.compile("^(?:7|0|(?:\\+94))[0-9]{9,10}$");
        CusContactMatcher = ContactPattern.matcher(txtContact.getText());

        if (!CusContactMatcher.matches()) {
            txtContact.requestFocus();
            lblContact.setText("invalid Contact");
        }

    }
}