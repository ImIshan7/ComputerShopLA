package lk.ijse.computershop.controller;
import com.jfoenix.controls.JFXTextArea;
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



    public void initialize() {

        ColEMID.setCellValueFactory(new PropertyValueFactory<>("EMID"));
        ColName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        ColAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        ColContact.setCellValueFactory(new PropertyValueFactory<>("Contact"));


        loadAllEmploy();
      //  searchEmploy();

      //  AddTable(searchText);

        setPattern();

        txtSerachID.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText=newValue;
          //  AddTable(searchText);


        });

        tblEmployee.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectedID = newValue.getEMID();

                System.out.println(newValue.getEMID());

                txtID.setText(newValue.getEMID());
                txtName.setText(newValue.getName());
                txtAddress.setText(newValue.getAddress());
                txtContact.setText(newValue.getContact());
            }
        } );

    }


    private void loadAllEmploy(){
        tblEmployee.getItems().clear();
        try {
            ArrayList<EmployDTO> allEmploy = employBO.getAllEmploy();

            for (EmployDTO c : allEmploy){
                tblEmployee.getItems().add(new EmployTm(c.getEMID(),c.getName(),c.getAddress(),c.getContact()));

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

//        Pattern userContactPattern = Pattern.compile("^(?:7|0|(?:\\+94))[0-9]{9,10}$");
//        dTelephoneMatcher = userContactPattern.matcher(txtTelephone.getText());
//
//        Pattern amountPattern = Pattern.compile("^[0-9]{1,}$");
//        dAmountMatcher = amountPattern.matcher(txtLoanAmount.getText());
//
//        Pattern nicPattern = Pattern.compile("^[0-9]{10}[vVxX]$");
//        dNicMatcher = nicPattern.matcher(txtNIC.getText());
    }



    private void setdata(EmployTm tm){

       txtID.setText(tm.getID());
       txtName.setText(tm.getName());
       txtAddress.setText(tm.getAddress());
       txtContact.setText(tm.getContact());
    }


    @FXML
    void btnAddOnAction(ActionEvent event) {

        String EMID = txtID.getText();
        String Name = txtName.getText();
        String Address = txtAddress.getText();
        String Contact = txtContact.getText();

      //  Employ employ = new Employ(EMID, Name, Address, Contact);

        setPattern();
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
            tblEmployee.getItems().add(new EmployTm(EMID,Name,Address,Contact));

           // AddTable(searchText);

            if (isAdded) {

                cleardata();

                new Alert(Alert.AlertType.CONFIRMATION, "Employ Added successfully!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }


//    private void searchEmploy() {
//        // search customer
//        FilteredList<EmployTm> filteredList = new FilteredList(obList, b -> true);
//
//        txtSerachID.textProperty().addListener((observable, oldValue, newValue) -> {
//            filteredList.setPredicate(employTm -> {
//                if (newValue == null || newValue.isEmpty()) {
//                    return true;
//                }
//                String lowerCaseFilter = newValue.toLowerCase();
//                if (employTm.getEMID().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//                    return true;
//                } else if (employTm.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//                    return true;
//                } else if (String.valueOf(employTm.getAddress()).indexOf(lowerCaseFilter) != -1) {
//                    return true;
//                } else if (String.valueOf(employTm.getContact()).indexOf(lowerCaseFilter) != -1) {
//                    return true;
//                } else {
//                    return false;
//                }
//            });
//        });
//
//        SortedList<EmployTm> sortedList = new SortedList(filteredList);
//        sortedList.comparatorProperty().bind(tblEmployee.comparatorProperty());
//        tblEmployee.setItems(sortedList);
//    }



//    private void  AddTable(String text) {
//        String searchText = "%" + text + "%";
//
//        try {
//            ObservableList<EmployTm> tmList = FXCollections.observableArrayList();
//
//
//            Connection connection = DBConnection.getInstance().getConnection();
//            String sql = "SELECT * From Employ WHERE Name LIKE ? || EMID LIKE?";
//            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.setString(1,searchText);
//            statement.setString(2,searchText);
//            ResultSet set = statement.executeQuery();
//
//            while (set.next()){
//                EmployTm employTm = new EmployTm(set.getString(1),set.getString(2),set.getString(3),
//                        set.getString(4));
//
//                tmList.add(employTm);
//            }
//
//
//            tblEmployee.setItems(tmList);
//
//        } catch (ClassNotFoundException | SQLException e)  {
//
//        }
//    }

   private void cleardata(){

        txtID.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();

   }

//    public static Employ Table(String EMID) throws SQLException, ClassNotFoundException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/computershop", "root", "1234");
//        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Employ WHERE EMID = ?");
//        pstm.setString(1, EMID);
//
//        ResultSet result = pstm.executeQuery();
//
//        if(result.next()) {
//            return new Employ(
//                    result.getString(1),
//                    result.getString(2),
//                    result.getString(3),
//                    result.getString(4)
//            );
//        }
//        return null;
//    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        Alert alert = new Alert(Alert.AlertType.WARNING, "Are You Sure You Want To Delete These Employee?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {


         //   boolean isDeleted = CrudUtil.execute("DELETE FROM Employ WHERE EMID=?", selectedID);

            String EMID = tblEmployee.getSelectionModel().getSelectedItem().getEMID();
            boolean isDeleted = employBO.deleteEmploy(EMID);

            if (isDeleted) {
               // AddTable(searchText);
                loadAllEmploy();
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Deleted!").show();
                cleardata();
            } else new Alert(Alert.AlertType.WARNING, "Something happened!").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

//        String Name = txtName.getText();
//        String Address = txtAddress.getText();
//        String Contact = txtContact.getText();

        //boolean isUpdate = CrudUtil.execute("UPDATE Employ set Name = ?, Address = ?,  Contact = ? WHERE EMID = ?", Name, Address,  Contact, selectedID);

        boolean isUpdate = employBO.updateEmploy(new EmployDTO(txtID.getText(),txtName.getText(),txtAddress.getText(),txtContact.getText()));

        if (isUpdate) {
          //  AddTable(searchText);
            loadAllEmploy();
            cleardata();
            new Alert(Alert.AlertType.CONFIRMATION, "Employee Update Successfully!").show();
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
