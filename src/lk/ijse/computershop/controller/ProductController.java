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
import lk.ijse.computershop.bo.custom.ProductBO;
import lk.ijse.computershop.dto.ProductDTO;
import lk.ijse.computershop.view.tm.ProductTm;


import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductController {

    public TableView<ProductTm> tblProduct;
    public TableColumn ColID;
    public TableColumn ColName;
    public TableColumn ColPrice;
    public TableColumn ColDESC;
    public TableColumn ColQTY;
    public JFXTextArea txtqty;
    public JFXTextArea txtSerchID;
    public Label lblPrdID;
    @FXML
    private AnchorPane ProductID;

    @FXML
    private JFXTextArea txtDesc;

    @FXML
    private JFXTextArea txtID;

    @FXML
    private JFXTextArea txtName;

    @FXML
    private JFXTextArea txtUnitPrice;

    private String searchText = "";
    String selectedID;

    private Matcher PrdIDMatcher;

    ProductBO productBO = (ProductBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.PRODUCT);

    public static ObservableList obList = FXCollections.observableArrayList();

    public void initialize() throws SQLException, ClassNotFoundException {

        obList.clear();

        ColID.setCellValueFactory(new PropertyValueFactory<>("PrdID"));
        ColName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        ColPrice.setCellValueFactory(new PropertyValueFactory<>("Unit_Price"));
        ColDESC.setCellValueFactory(new PropertyValueFactory<>("Description"));
        ColQTY.setCellValueFactory(new PropertyValueFactory<>("QTY"));


        ArrayList arrayList = productBO.getAllProduct();

        for (Object e : arrayList){
            obList.add(e);

        }

        setPattern();

        tblProduct.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectedID = newValue.getPrdID();

                System.out.println(newValue.getPrdID());

                txtID.setText(newValue.getPrdID());
                txtName.setText(newValue.getName());
                txtUnitPrice.setText(String.valueOf(newValue.getUnit_Price()));
                txtDesc.setText(newValue.getDescription());
                txtqty.setText(String.valueOf(newValue.getQty()));
            }
        } );
    }


    /*private void loadAllProduct(){
        tblProduct.getItems().clear();
        try {
            ArrayList<ProductDTO> allProduct = productBO.getAllProduct();

            for (ProductDTO c : allProduct){
                tblProduct.getItems().add(new ProductTm(c.getPrdID(),c.getName(),c.getUnit_Price(),c.getDescription(),c.getQty()));

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/


    public void setPattern() {

        Pattern IDMatcher = Pattern.compile("^(P0)([0-9]{1})([0-9]{1,})$");
        PrdIDMatcher = IDMatcher.matcher(txtID.getText());
    }



    @FXML
    void btnAddOnAction(ActionEvent event) {

        String PrdID = txtID.getText();
        String Name = txtName.getText();
        Double Unit_Price = Double.valueOf(txtUnitPrice.getText());
        String Description = txtDesc.getText();
        int QTY = Integer.parseInt(txtqty.getText());

       // Product product = new Product(PrdID, Name, Unit_Price, Description, QTY);

        setPattern();
        if (PrdIDMatcher.matches()) {

        }else{
            txtID.requestFocus();
            lblPrdID.setText("invalid ID ");
        }

        try {
            boolean isAdded = productBO.addProduct(new ProductDTO(PrdID,Name,Unit_Price,Description,QTY));

            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Product Added  Successfully!").show();

                cleardata();
                initialize();

            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

   /* private void AddTable(String text) {

        String searchText = "%" + text + "%";

        try {
            ObservableList<ProductTm> tmList = FXCollections.observableArrayList();


            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "SELECT * From Product WHERE PrdID LIKE ? || Name LIKE?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, searchText);
            statement.setString(2, searchText);
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                ProductTm productTm = new ProductTm(set.getString(1), set.getString(2), set.getDouble(3),
                        set.getString(4), set.getInt(5));

                tmList.add(productTm);
            }


            tblProduct.setItems(tmList);

        } catch (ClassNotFoundException | SQLException e) {

        }
    }*/

    private void cleardata() {

        txtID.clear();
        txtName.clear();
        txtDesc.clear();
        txtqty.clear();
        txtUnitPrice.clear();
    }


//    public static Product Table(String PrdID) throws SQLException, ClassNotFoundException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/computershop", "root", "1234");
//        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Product WHERE PrdID = ?");
//        pstm.setString(1, PrdID);
//
//        ResultSet result = pstm.executeQuery();
//
//        if (result.next()) {
//            return new Product(
//                    result.getString(1),
//                    result.getString(2),
//                    Double.valueOf(String.valueOf(result.getDouble(3))),
//                    result.getString(4),
//                    result.getInt(5)
//            );
//        }
//        return null;
//
//
//    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) ProductID.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/DashBoard.fxml")))));

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        Alert alert = new Alert(Alert.AlertType.WARNING, "Are You Sure You Want To Delete These Product?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {


       //     boolean isDeleted = CrudUtil.execute("DELETE FROM Product where PrdID=?", selectedID);

            String PrdID = tblProduct.getSelectionModel().getSelectedItem().getPrdID();
            boolean isDeleted = productBO.deleteProduct(PrdID);

            if (isDeleted) {
              //  AddTable(searchText);
                new Alert(Alert.AlertType.CONFIRMATION, "Product Deleted!").show();

                cleardata();
                initialize();

            } else new Alert(Alert.AlertType.WARNING, "Something happened!").show();
        }

    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        String PrdID = txtID.getText();
        String Name = txtName.getText();
        Double Unit_Price = Double.valueOf(txtUnitPrice.getText());
        String Description = txtDesc.getText();
        int QTY = Integer.parseInt(txtqty.getText());

     //   boolean isUpdate = CrudUtil.execute("UPDATE Product set Name = ?, UnitPrice = ?,  Descripion = ?, QTY = ? WHERE PrdID = ?", Name, Unit_Price, Description,QTY, selectedID);

        boolean isUpdate = productBO.updateProduct(new ProductDTO(PrdID,Name,Unit_Price,Description,QTY));
        if (isUpdate) {

            new Alert(Alert.AlertType.CONFIRMATION, "Product Update Successfully!").show();

            cleardata();
            initialize();

        } else {
            new Alert(Alert.AlertType.WARNING, "Something happened!").show();
        }

    }



   public void KeyPressID(KeyEvent keyEvent) {
/*
            String code = txtSerchID.getText();
            String sql = "SELECT FROM Product WHERE PrdID";

            try{  Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/computershop", "root", "1234");
              PreparedStatement pre = connection.prepareStatement(sql);

                pre.setObject(1,code);
                ResultSet executeQuery = pre.executeQuery();
               if(executeQuery.next()){

                    txtID.setText(executeQuery.getString("PrdID"));
                    txtName.setText(executeQuery.getString("Name"));
                    txtUnitPrice.setText(executeQuery.getString("UnitPrice"));
                   txtDesc.setText(executeQuery.getString("Descripion"));
                }

            } catch (SQLException e) {
               throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }*/
       }

    public void txtPrdIDKeyTypeOnAction(KeyEvent keyEvent) {
        lblPrdID.setText("");

        Pattern IDMatcher = Pattern.compile("^(P0)([0-9]{1})([0-9]{1,})$");
        PrdIDMatcher = IDMatcher.matcher(txtID.getText());

        if (!PrdIDMatcher.matches()) {
            txtID.requestFocus();
            lblPrdID.setText("invalid ID");
        }
    }
}





