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
import lk.ijse.computershop.bo.custom.EmployBO;
import lk.ijse.computershop.dto.EmployDTO;
import lk.ijse.computershop.view.tm.CustomerTm;
import lk.ijse.computershop.view.tm.EmployTm;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static lk.ijse.computershop.controller.SupplierOrderController.obList;

public class EmployController {

    public JFXTextArea txtSerachID;
    public TableView<EmployTm> tblEmployee;
    public TableColumn ColEMID;
    public TableColumn ColName;
    public TableColumn ColAddress;
    public TableColumn ColContact;
    public Label lblEmID;
    public Label lblName;
    public Label lblAddress;
    public Label lblContact;
    @FXML
    private AnchorPane EmployID;

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

    private Matcher EmIDMatcher;
    private Matcher EMNameMatcher;
    private Matcher EmAddressMatcher;
    private Matcher EMContactMatcher;


   EmployBO employBO = (EmployBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.EMPLOY);

    public static ObservableList obList = FXCollections.observableArrayList();




    public void initialize() throws SQLException, ClassNotFoundException {

        obList.clear();

        ColEMID.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        ColContact.setCellValueFactory(new PropertyValueFactory<>("contact"));



        ArrayList arrayList = employBO.getAllEmploy();

        for (Object e : arrayList){
            obList.add(e);

        }

        setPattern();
        searchPart();

        tblEmployee.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectedID = newValue.getId();

                System.out.println(newValue.getId());

                txtID.setText(newValue.getId());
                txtName.setText(newValue.getName());
                txtAddress.setText(newValue.getAddress());
                txtContact.setText(newValue.getContact());
            }
        } );

    }


    public void setPattern(){

        Pattern userIdPattern = Pattern.compile("^(E0)([0-9]{1})([0-9]{1,})$");
        EmIDMatcher = userIdPattern.matcher(txtID.getText());

        Pattern EmployeeNamePattern = Pattern.compile("^([a-zA-Z]{4,})$");
        EMNameMatcher = EmployeeNamePattern.matcher(txtName.getText());

        Pattern userAddressPattern = Pattern.compile("^([a-zA-Z]{4,})$");
        EmAddressMatcher = userAddressPattern.matcher(txtAddress.getText());

        Pattern userContactPattern = Pattern.compile("^(?:7|0|(?:\\+94))[0-9]{9,10}$");
        EMContactMatcher = userContactPattern.matcher(txtContact.getText());


    }


    @FXML
    void btnAddOnAction(ActionEvent event) {

        String EMID = txtID.getText();
        String Name = txtName.getText();
        String Address = txtAddress.getText();
        String Contact = txtContact.getText();


        if (EmIDMatcher.matches()) {
            if (EMNameMatcher.matches()) {
                if (EmAddressMatcher.matches()) {
                    if (EMContactMatcher.matches()) {


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
            lblEmID.setText("invalid ID ");

        }


        try {
            boolean isAdded = employBO.addEmploy(new EmployDTO(txtID.getText(),txtName.getText(),txtAddress.getText(),txtContact.getText()));

            if (isAdded) {

                new Alert(Alert.AlertType.CONFIRMATION, "Employ Added successfully!").show();
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
        txtContact.clear();

   }


    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        Alert alert = new Alert(Alert.AlertType.WARNING, "Are You Sure You Want To Delete These Employee?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {

            String EMID = tblEmployee.getSelectionModel().getSelectedItem().getId();
            boolean isDeleted = employBO.deleteEmploy(EMID);

            if (isDeleted) {

                new Alert(Alert.AlertType.CONFIRMATION, "Employee Deleted!").show();

                cleardata();
                initialize();

            } else new Alert(Alert.AlertType.WARNING, "Something happened!").show();
        }
    }

    private void searchPart() {
        // search customer
        FilteredList<EmployTm> filteredList = new FilteredList(obList, b -> true);

        txtSerachID.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(employTm -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (employTm.getId().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (employTm.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(employTm.getAddress()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(employTm.getContact()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<EmployTm> sortedList = new SortedList(filteredList);
        sortedList.comparatorProperty().bind(tblEmployee.comparatorProperty());
        tblEmployee.setItems(sortedList);
    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        boolean isUpdate = employBO.updateEmploy(new EmployDTO(txtID.getText(),txtName.getText(),txtAddress.getText(),txtContact.getText()));

        if (isUpdate) {

            new Alert(Alert.AlertType.CONFIRMATION, "Employee Update Successfully!").show();

            cleardata();
            initialize();

        } else {
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
        }

    }


    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) EmployID.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/DashBoard.fxml")))));
    }

    public void txtEmIDKeyTypeOnAction(KeyEvent keyEvent) {
        lblEmID.setText("");

        Pattern userIdPattern = Pattern.compile("^(E0)([0-9]{1})([0-9]{1,})$");
        EmIDMatcher = userIdPattern.matcher(txtID.getText());

        if (!EmIDMatcher.matches()) {
            txtID.requestFocus();
            lblEmID.setText("invalid ID");
        }
    }

    public void txtNameKeyTypeOnAction(KeyEvent keyEvent) {

        lblName.setText("");

        Pattern EmployeeNamePattern = Pattern.compile("^([a-zA-Z]{4,})$");
        EMNameMatcher = EmployeeNamePattern.matcher(txtName.getText());

        if (!EMNameMatcher.matches()) {
            txtName.requestFocus();
            lblName.setText("invalid Name");
        }
    }

    public void txtAddressKeyTypeOnAction(KeyEvent keyEvent) {


        lblAddress.setText("");

        Pattern userAddressPattern = Pattern.compile("^([a-zA-Z]{4,})$");
        EmAddressMatcher = userAddressPattern.matcher(txtAddress.getText());

        if (!EmAddressMatcher.matches()) {
            txtAddress.requestFocus();
            lblAddress.setText("invalid Address");
        }
    }

    public void txtContactKeyTypeOnAction(KeyEvent keyEvent) {
        lblContact.setText("");

        Pattern userContactPattern = Pattern.compile("^(?:7|0|(?:\\+94))[0-9]{9,10}$");
        EMContactMatcher = userContactPattern.matcher(txtContact.getText());

        if (!EMContactMatcher.matches()) {
            txtContact.requestFocus();
            lblContact.setText("invalid Contact Number");
        }
    }
}
