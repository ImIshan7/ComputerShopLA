package lk.ijse.computershop.controller;

import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import rex.utils.S;

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


    public void initialize() throws SQLException, ClassNotFoundException {


        ColID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        ColName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        ColAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        ColContact.setCellValueFactory(new PropertyValueFactory<>("Contact"));

       AddTable(searchText);
        setPattern();
        //serachCustomers();
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText=newValue;
            AddTable(searchText);

        });

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectedID = newValue.getID();

                System.out.println(newValue.getID());

                txtID.setText(newValue.getID());
                txtName.setText(newValue.getName());
                txtAddress.setText(newValue.getAddress());
                txtContact.setText(newValue.getContact());
            }
        } );
        loadAllCustomers();
        }



    private void loadAllCustomers(){
        tblCustomer.getItems().clear();
        try {
            ArrayList<CustomerDTO> allCustomers = customerBO.getAllCustomers();

            for (CustomerDTO c : allCustomers){
                tblCustomer.getItems().add(new CustomerTm(c.getID(),c.getName(),c.getAddress(),c.getContact()));

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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


    private void setdata(CustomerTm tm){
        txtID.setText(tm.getID());
        txtName.setText(tm.getName());
        txtAddress.setText(tm.getAddress());
        txtContact.setText(tm.getContact());
    }


    @FXML
    void btnRegOnAction (ActionEvent event) throws SQLException, ClassNotFoundException {
        String ID = txtID.getText();
        String Name = txtName.getText();
        String Address = txtAddress.getText();
        String Contact = txtContact.getText();

       // Customer customer = new Customer(ID, Name, Address, Contact);

        setPattern();
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
              tblCustomer.getItems().add(new CustomerTm(ID,Name,Address,Contact));

           AddTable(searchText);
//            cleardata();

            if (isAdded) {
                cleardata();
                AddTable(searchText);
               // loadAllCustomers();

                new Alert(Alert.AlertType.CONFIRMATION, "Customer Register Successfully!").show();

            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    private void  AddTable(String text) {

        String searchText = "%" + text + "%";

        try {
            ObservableList<CustomerTm> tmList = FXCollections.observableArrayList();


            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "SELECT * From Customer WHERE Name LIKE ? || CusID LIKE ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,searchText);
            statement.setString(2,searchText);
            ResultSet set = statement.executeQuery();

            while (set.next()){
                CustomerTm customerTm = new CustomerTm(set.getString(1),set.getString(2),set.getString(3),
                        set.getString(4));

                tmList.add(customerTm);
            }


            tblCustomer.setItems(tmList);

        } catch (ClassNotFoundException | SQLException e)  {

        }
    }



    private  void cleardata(){
        txtID.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
    }



//    public static Customer Table(String CusID) throws SQLException, ClassNotFoundException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/computershop", "root", "1234");
//        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Customer WHERE CusID = ?");
//        pstm.setString(1, CusID);
//
//        ResultSet result = pstm.executeQuery();
//
//        if(result.next()) {
//            return new Customer(
//                    result.getString(1),
//                    result.getString(2),
//                    result.getString(3),
//                    result.getString(4)
//            );
//        }
//        return null;
//    }


    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
//        IshanID.getChildren().clear();

        Stage stage = (Stage) IshanID.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/DashBoard.fxml")))));




    }


    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

//
//        String Contact = txtContact.getText();
//        String Address = txtAddress.getText();
//        String Name = txtName.getText();

     //   boolean isUpdate = CrudUtil.execute("UPDATE Customer set Name = ?, Address = ?, Contact = ? WHERE CusID = ?", Name, Address, Contact, selectedID);
      //  AddTable(searchText);


      boolean isUpdate =  customerBO.updateCustomer(new CustomerDTO(txtID.getText(),txtName.getText(),txtAddress.getText(),txtContact.getText()));

            if (isUpdate) {
                AddTable(searchText);
                //loadAllCustomers();
                cleardata();
                txtSearch.clear();
                cleardata();
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Update Successfully!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }

        }


    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Alert alert = new Alert(Alert.AlertType.WARNING, "Are You Sure You Want To Delete These Customer ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();

//
//        if (result.get() == ButtonType.YES) {
//            boolean isDeleted = CrudUtil.execute("DELETE FROM Customer where CusID=?", selectedID);

        String CusID = tblCustomer.getSelectionModel().getSelectedItem().getID();

               boolean isDeleted = customerBO.deleteCustomer(CusID);

            if (isDeleted) {
                AddTable(searchText);
                //loadAllCustomers();
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Deleted!").show();
                cleardata();
            } else new Alert(Alert.AlertType.WARNING, "Something happened!").show();
        }
  //  }



//    private void serachCustomers() throws SQLException, ClassNotFoundException {
//
//        String id = tblCustomer.getSelectionModel().getSelectedItem().getID();
//        customerBO.searchCustomer(id);
//    }



//    public static ArrayList<Customer> getAllCustomer() throws ClassNotFoundException, SQLException {
//
//        Connection connection = DBConnection.getInstance().getConnection();
//        ResultSet result = connection.prepareStatement("SELECT * FROM Customer").executeQuery();
//        ArrayList<Customer> data = new ArrayList();
//        while (result.next()) {
//            Customer c = new Customer(
//                    result.getString(1),
//                    result.getString(2),
//                    result.getString(3),
//                    result.getString(4)
//            );
//
//            data.add(c);
//        }
//        return data;
//    }

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