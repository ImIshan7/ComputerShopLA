package lk.ijse.computershop.controller;

import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import lk.ijse.computershop.dto.EmployDTO;
import lk.ijse.computershop.dto.ServiceDTO;
import lk.ijse.computershop.model.ServiceModel;
import lk.ijse.computershop.to.Service;
import lk.ijse.computershop.util.CrudUtil;
import lk.ijse.computershop.view.tm.CustomerTm;
import lk.ijse.computershop.view.tm.EmployTm;
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


    public void initialize() {

        ColSerID.setCellValueFactory(new PropertyValueFactory<>("SerID"));
        ColEmID.setCellValueFactory(new PropertyValueFactory<>("EMID"));
        ColDESC.setCellValueFactory(new PropertyValueFactory<>("Descripion"));
        ColPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));

        AddTable(searchText);
        setPattern();

        txtSID.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText = newValue;
            AddTable(searchText);
        });

        tblService.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectedID = newValue.getSerID();

                System.out.println(newValue.getSerID());

                txtSerID.setText(newValue.getSerID());
                txtEmID.setText(newValue.getEMID());
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


    private void setdata(ServiceTm tm) {

        txtSerID.setText(tm.getSerID());
        txtEmID.setText(tm.getEMID());
        txtDESC.setText(tm.getDescripion());
        txtPrice.setText(String.valueOf(tm.getPrice()));

    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

        String SerID = txtSerID.getText();
        String EMID = txtEmID.getText();
        String Desc = txtDESC.getText();
        Double Price = Double.valueOf(txtPrice.getText());


      //  Service service = new Service(SerID, EMID, Desc, Price);

        setPattern();
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
            /*boolean isAdded = serviceBO.addService(new ServiceDTO(txtSerID.getText(),txtEmID.getText(),txtDESC.getText(),txtPrice.getText()));
            tblService.getItems().add(new ServiceTm(SerID,EMID,Desc,Price));*/
            boolean isAdded = serviceBO.addService(new ServiceDTO(SerID, EMID, Desc, Price));

            AddTable(searchText);

            if (isAdded) {

                cleardata();
                initialize();

                new Alert(Alert.AlertType.CONFIRMATION, "Service Added Sccssefully!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    private void AddTable(String text) {

        String searchText = "%" + text + "%";

        try {
            ObservableList<ServiceTm> tmList = FXCollections.observableArrayList();

            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "SELECT * From Service WHERE SerID LIKE ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, searchText);
            //statement.setString(2,searchText);

            ResultSet set = statement.executeQuery();

            while (set.next()) {
                ServiceTm serviceTm = new ServiceTm(set.getString(1), set.getString(2), set.getString(3), set.getDouble(4));

                tmList.add(serviceTm);
            }

            tblService.setItems(tmList);

        } catch (ClassNotFoundException | SQLException e) {

        }
    }


    private void cleardata() {

        txtEmID.clear();
        txtSerID.clear();
        txtDESC.clear();
        txtPrice.clear();
    }


    public static Service Table(String SerID) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/computershop", "root", "1234");
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Service WHERE SerID = ?");
        pstm.setString(1, SerID);

        ResultSet result = pstm.executeQuery();

        if (result.next()) {
            return new Service(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getDouble(4)

            );
        }
        return null;
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

            cleardata();
            initialize();
            AddTable(searchText);

            new Alert(Alert.AlertType.CONFIRMATION, "Service Update Successfully!").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
        }

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