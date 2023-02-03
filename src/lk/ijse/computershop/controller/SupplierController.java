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
import lk.ijse.computershop.bo.custom.SupplierBO;
import lk.ijse.computershop.db.DBConnection;
import lk.ijse.computershop.dto.EmployDTO;
import lk.ijse.computershop.dto.ServiceDTO;
import lk.ijse.computershop.dto.SupplierDTO;
import lk.ijse.computershop.model.SupplierModel;
import lk.ijse.computershop.to.Customer;
import lk.ijse.computershop.to.Supplier;
import lk.ijse.computershop.util.CrudUtil;
import lk.ijse.computershop.view.tm.EmployTm;
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

    public void initialize() {

        ColSupID.setCellValueFactory(new PropertyValueFactory<>("SupID"));
        ColName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        ColAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        ColBrand.setCellValueFactory(new PropertyValueFactory<>("Brand"));
        ColPrice.setCellValueFactory(new PropertyValueFactory<>("Unit_Price"));
        ColQTY.setCellValueFactory(new PropertyValueFactory<>("QTY"));

        loadAllSupplier();

        AddTable(searchText);
        setPattern();

        txtSearchID.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText=newValue;
           AddTable(searchText);

        });

        tblSupplier.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectedID = newValue.getSupID();

                System.out.println(newValue.getSupID());

                txtID.setText(newValue.getSupID());
                txtName.setText(newValue.getName());
                txtAddress.setText(newValue.getAddress());
                txtBrand.setText(newValue.getBrand());
                txtPrice.setText(String.valueOf(newValue.getUnit_Price()));
                txtQTY.setText(String.valueOf(newValue.getQTY()));
            }
        } );
    }


    private void loadAllSupplier(){
        tblSupplier.getItems().clear();
        try {
            ArrayList<SupplierDTO> allSupplier = supplierBO.getAllSupplier();

            for (SupplierDTO c : allSupplier){
                tblSupplier.getItems().add(new SupplierTm(c.getSupID(),c.getName(),c.getAddress(),c.getBrand(),c.getUnit_Price(),c.getQTY()));

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



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


    private void setdata(SupplierTm tm){
        txtID.setText(tm.getSupID());
        txtName.setText(tm.getName());
        txtAddress.setText(tm.getAddress());
        txtBrand.setText(tm.getBrand());
        txtPrice.setText(String.valueOf(tm.getUnit_Price()));
        txtQTY.setText(String.valueOf(tm.getQTY()));
    }
    @FXML
    void btnAddOnAction(ActionEvent event) {

        String SupID = txtID.getText();
        String Name = txtName.getText();
        String Address = txtAddress.getText();
        String Brand = txtBrand.getText();
        Double Unit_Price = Double.valueOf(txtPrice.getText());
        int QTY = Integer.parseInt(txtQTY.getText());


      //  Supplier supplier = new Supplier(SupID, Name, Address, Brand,Unit_Price,QTY);

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
        //    tblSupplier.getItems().add(new SupplierTm(SupID,Name,Address,Brand,Unit_Price,QTY));


           // loadAllSupplier();
           AddTable(searchText);


            if (isAdded) {

                cleardata();


                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Added  Successfully!").show();
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
            ObservableList<SupplierTm> tmList = FXCollections.observableArrayList();


            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "SELECT * From Supplier WHERE SupID LIKE? || Name LIKE ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,searchText);
            statement.setString(2,searchText);
            ResultSet set = statement.executeQuery();


            while (set.next()){
                SupplierTm supplierTm = new SupplierTm(set.getString(1),set.getString(2),set.getString(3),
                        set.getString(4) ,set.getDouble(5),set.getInt(6));

                tmList.add(supplierTm);
            }


            tblSupplier.setItems(tmList);

        } catch (ClassNotFoundException | SQLException e)  {

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



//    public static Supplier Table(String SupID) throws SQLException, ClassNotFoundException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/computershop", "root", "1234");
//        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Supplier WHERE SupID = ?");
//        pstm.setString(1, SupID);
//
//        ResultSet result = pstm.executeQuery();
//
//        if(result.next()) {
//            return new Supplier(
//                    result.getString(1),
//                    result.getString(2),
//                    result.getString(3),
//                    result.getString(4),
//                    result.getDouble(5),
//                    result.getInt(6)
//            );
//        }
//        return null;
//
//
//    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        Alert alert = new Alert(Alert.AlertType.WARNING, "Are You Sure You Want To Delete These Supplier?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
          //  boolean isDeleted = CrudUtil.execute("DELETE FROM Supplier where SupID=?", selectedID);
            String SupID = tblSupplier.getSelectionModel().getSelectedItem().getSupID();
            boolean isDeleted = supplierBO.deleteSupplier(SupID);

            if (isDeleted) {
               AddTable(searchText);
              //  loadAllSupplier();

                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Deleted!").show();
                cleardata();
            } else new Alert(Alert.AlertType.WARNING, "Something happened!").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {


        String SupID = txtID.getText();
        String Name = txtName.getText();
        String Address = txtAddress.getText();
        String Brand = txtBrand.getText();
        Double Unit_Price = Double.valueOf(txtPrice.getText());
        int QTY = Integer.parseInt(txtQTY.getText());

       // boolean isUpdate = CrudUtil.execute("UPDATE Supplier set Name = ?, Address = ?, Brand = ?, Unit_Price = ?, QTY = ? WHERE SupID = ?", Name, Address, Brand,Unit_Price,QTY,selectedID);

        boolean isUpdate = supplierBO.updateSupplier(new SupplierDTO(SupID,Name,Address,Brand, Unit_Price,QTY));

       AddTable(searchText);
       // loadAllSupplier();



        if (isUpdate) {

            cleardata();
          //  txtSearchID.clear();
           // cleardata();

            new Alert(Alert.AlertType.CONFIRMATION, "Supplier Update Successfully!").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
        }


    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)SupplierID.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/DashBoard.fxml")))));
    }


//    public static ArrayList<Supplier> getAllSuppliers() throws ClassNotFoundException, SQLException {
//        Connection connection = DBConnection.getInstance().getConnection();
//        ResultSet result = connection.prepareStatement("SELECT * FROM Supplier").executeQuery();
//        ArrayList<Supplier> data = new ArrayList();
//        while (result.next()) {
//            Supplier s = new Supplier(
//                    result.getString(1),
//                    result.getString(2),
//                    result.getString(3),
//                    result.getString(4),
//                    result.getDouble(5),
//                    result.getInt(6)
//            );
//
//            data.add(s);
//        }
//        return data;
//    }

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
