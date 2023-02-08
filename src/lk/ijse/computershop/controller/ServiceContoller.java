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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.computershop.bo.BOFactory;
import lk.ijse.computershop.bo.custom.ServiceBO;
import lk.ijse.computershop.db.DBConnection;
import lk.ijse.computershop.dto.ServiceDTO;
import lk.ijse.computershop.entity.Service;
import lk.ijse.computershop.view.tm.CustomerTm;
import lk.ijse.computershop.view.tm.ServiceTm;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiceContoller {

    public JFXTextArea txtSearchID;
    public JFXTextArea txtDESC;
    public TableView<ServiceTm> tblService;
    public TableColumn ColSerID;
    public TableColumn ColEmID;
    public TableColumn ColDESC;
    public JFXTextArea txtPrice;
    public TableColumn ColPrice;
    public JFXTextArea txtSID;
    public Label lblServiceId;
    public Label lblEmID;
    @FXML
    private AnchorPane ServiceID;

    @FXML
    private JFXTextArea txtEmID;

    @FXML
    private JFXTextArea txtSerID;

    @FXML
    private JFXTextArea txtType;

    private String searchText = "";
    String selectedID;

    private Matcher SerIDMatcher;
    private Matcher EmIDMatcher;


    ServiceBO serviceBO = (ServiceBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.SERVICE);

    public static ObservableList obList = FXCollections.observableArrayList();


    public void initialize() throws SQLException, ClassNotFoundException {

        obList.clear();

        ColSerID.setCellValueFactory(new PropertyValueFactory<>("serID"));
        ColEmID.setCellValueFactory(new PropertyValueFactory<>("emID"));
        ColDESC.setCellValueFactory(new PropertyValueFactory<>("descripion"));
        ColPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        setPattern();

       ArrayList arrayList = serviceBO.getAllService();

        for (Object e : arrayList){
            obList.add(e);
        }

        searchPart();


        tblService.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectedID = newValue.getSerID();

                System.out.println(newValue.getSerID());

                txtSerID.setText(newValue.getSerID());
                txtEmID.setText(newValue.getEmID());
                txtDESC.setText(newValue.getDescripion());
                txtPrice.setText(String.valueOf(newValue.getPrice()));
            }
        });
    }

    public void setPattern() {

        Pattern SerIdPattern = Pattern.compile("^(SR0)([0-9]{1})([0-9]{1,})$");
        SerIDMatcher = SerIdPattern.matcher(txtSerID.getText());

        Pattern userIdPattern = Pattern.compile("^(E0)([0-9]{1})([0-9]{1,})$");
        EmIDMatcher = userIdPattern.matcher(txtEmID.getText());

    }


    @FXML
    void btnAddOnAction(ActionEvent event) {

        String SerID = txtSerID.getText();
        String EMID = txtEmID.getText();
        String Desc = txtDESC.getText();
        Double Price = Double.valueOf(txtPrice.getText());


        if (SerIDMatcher.matches()) {
            if (EmIDMatcher.matches()) {

            }else {
                txtEmID.requestFocus();
                lblEmID.setText("invalid EmployeeID ");
            }
        }else{
            txtSerID.requestFocus();
            lblServiceId.setText("invalid ServiceID ");
        }

        try {
            boolean isAdded = serviceBO.addService(new ServiceDTO(SerID, EMID, Desc, Price));

            if (isAdded) {

                new Alert(Alert.AlertType.CONFIRMATION, "Service Added Sccssefully!").show();

                cleardata();
                initialize();

            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    private void cleardata() {

        txtEmID.clear();
        txtSerID.clear();
        txtDESC.clear();
        txtPrice.clear();
    }


    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) ServiceID.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/DashBoard.fxml")))));

    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {


        String SerID = txtSerID.getText();
        String EMID = txtEmID.getText();
        String Desc = txtDESC.getText();
        Double Price = Double.valueOf(txtPrice.getText());


        boolean isUpdate =serviceBO.updateService(new ServiceDTO(SerID, EMID, Desc, Price));

        if (isUpdate) {


            new Alert(Alert.AlertType.CONFIRMATION, "Service Update Successfully!").show();

            cleardata();
            initialize();

        } else {
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
        }

    }

    private void searchPart() {
        // search customer
        FilteredList<ServiceTm> filteredList = new FilteredList(obList, b -> true);

        txtSID.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(serviceTm -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (serviceTm.getSerID().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (serviceTm.getEmID().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(serviceTm.getDescripion()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(serviceTm.getPrice()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<ServiceTm> sortedList = new SortedList(filteredList);
        sortedList.comparatorProperty().bind(tblService.comparatorProperty());
        tblService.setItems(sortedList);
    }


    public void txtSerIDKeyTypeOnAction(KeyEvent keyEvent) {

        lblServiceId.setText("");

        Pattern SerIdPattern = Pattern.compile("^(SR0)([0-9]{1})([0-9]{1,})$");
        SerIDMatcher = SerIdPattern.matcher(txtSerID.getText());

        if (!SerIDMatcher.matches()) {
            txtSerID.requestFocus();
            lblServiceId.setText("invalid ID");
        }
    }


    public void txtEmIDKeyAction(KeyEvent keyEvent) {
        lblEmID.setText("");

        Pattern userIdPattern = Pattern.compile("^(E0)([0-9]{1})([0-9]{1,})$");
        EmIDMatcher = userIdPattern.matcher(txtEmID.getText());

        if (!EmIDMatcher.matches()) {
            txtEmID.requestFocus();
            lblEmID.setText("invalid ID");
        }
    }
}