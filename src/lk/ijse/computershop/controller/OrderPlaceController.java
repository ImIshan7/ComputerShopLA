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
import lk.ijse.computershop.bo.BOFactory;
import lk.ijse.computershop.bo.custom.OrderPlaceBO;
import lk.ijse.computershop.model.OrdersModel;
import lk.ijse.computershop.model.PlaceOrderModel;
import lk.ijse.computershop.entity.OrderDetail;
import lk.ijse.computershop.entity.Orders;
import lk.ijse.computershop.view.tm.CartTm;


import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;


public class OrderPlaceController {


    public Label lblOrderID;
    public Label lbldate;

    public Label lbltime;
    public TableView<CartTm> tblOrderPlace;
    public TableColumn ColPrdID;
    public TableColumn ColDESC;
    public TableColumn ColUnitPrice;
    public TableColumn ColQTY;
    public TableColumn ColTotal;
    public TableColumn<CartTm,Button> ColOption;
    public JFXTextArea txtOrderQTY;
    @FXML
    private AnchorPane OrderID;

    @FXML
    private JFXComboBox<String> cmbCusID;

    @FXML
    private JFXComboBox<String> cmbPrdID;

    @FXML
    private Label lblTotal;

    @FXML
    private JFXTextArea txtCusAddress;

    @FXML
    private JFXTextArea txtCusContact;

    @FXML
    private JFXTextArea txtCusName;

    @FXML
    private JFXTextArea txtPrdDESC;

    @FXML
    private JFXTextArea txtPrdName;

    @FXML
    private JFXTextArea txtPrdQTY;

    @FXML
    private JFXTextArea txtPrdUnitPrice;

    OrderPlaceBO orderPlaceBO = (OrderPlaceBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.OP);


    public void initialize() {

        ColPrdID.setCellValueFactory(new PropertyValueFactory<>("PrdID"));
        ColDESC.setCellValueFactory(new PropertyValueFactory<>("Description"));
        ColUnitPrice.setCellValueFactory(new PropertyValueFactory<>("Unit_Price"));
        ColQTY.setCellValueFactory(new PropertyValueFactory<>("Order_QTY"));
        ColTotal.setCellValueFactory(new PropertyValueFactory<>("Total"));
        ColOption.setCellValueFactory(new PropertyValueFactory<CartTm,Button>("Button"));

        loadOrderDate();
        loadOrderTime();

        try {
            loadAllCustomer();
            loadAllProduct();
            loadNextOrderId();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);


        }


//        cmbCusID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue != null) {
//                setCustomers();
//            }
//        });

        cmbPrdID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setProducts();
            }
        });


    }

    private void loadAllCustomer() throws SQLException, ClassNotFoundException {



//        for (Customer customer : CustomerController.getAllCustomer()) {
//            cmbCusID.getItems().add(customer.getID());
//
//
//        }

    }

    private void loadAllProduct() throws SQLException, ClassNotFoundException {

//        for (Product product : ProductController.getAllProduct()) {
//            cmbPrdID.getItems().add(product.getPrdID());
//        }

    }

//    private void setCustomers() {
//        try {
//            for (Customer customer : CustomerController.getAllCustomer()) {
//
//                if (customer.getID().equals(cmbCusID.getValue())) {
//                    txtCusName.setText(customer.getName());
//                    txtCusAddress.setText(customer.getAddress());
//                    txtCusContact.setText(customer.getContact());
//                }
//            }
//
//
//        } catch (Exception e) {
//
//        }
//    }

    private void setProducts() {
//        try {
//            for (Product product : ProductController.getAllProduct()) {
//
//                if (product.getPrdID().equals(cmbPrdID.getValue())) {
//                    txtPrdName.setText(product.getName());
//                    txtPrdDESC.setText(product.getDescription());
//                    txtPrdUnitPrice.setText(String.valueOf(product.getUnit_Price()));
//                    txtPrdQTY.setText(String.valueOf(product.getQTY()));
//                }
//            }
//
//
//        } catch (Exception e) {
//
//        }
    }


    private void loadOrderDate() {
        lbldate.setText(String.valueOf(LocalDate.now()));
    }

    private void loadOrderTime() {
        lbltime.setText(String.valueOf(LocalTime.now()));
    }


    ObservableList<CartTm> obList = FXCollections.observableArrayList();


    @FXML
    void btnAddOnAction(ActionEvent actionEvent) {

        double unitPrice = Double.parseDouble(txtPrdUnitPrice.getText());
        int qty = Integer.parseInt((txtOrderQTY.getText()));
        double total = qty * unitPrice;
        Button button = new Button("Delete");

        int row = isAlredyExists(cmbPrdID.getValue());
        if (row == -1) {
            CartTm tm = new CartTm(cmbPrdID.getValue(), txtPrdDESC.getText(), unitPrice, qty, total, button);
            obList.add(tm);
            tblOrderPlace.setItems(obList);
        } else {
            int tempQty = obList.get(row).getOrder_QTY() + qty;
            double tempTotal = unitPrice * tempQty;
            obList.get(row).setOrder_QTY(tempQty);
            obList.get(row).setTotal(tempTotal);
            tblOrderPlace.refresh();
        }
        calculateTotal();
        cmbPrdID.requestFocus();


        button.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you Sure Delete Record", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();

            if (buttonType.get() == ButtonType.YES) {
                for (CartTm tm : obList
                ) {
                    if (tm.getPrdID().equals(tm.getPrdID())) {
                        obList.remove(tm);
                        calculateTotal();
                        tblOrderPlace.refresh();
                        return;
                    }
                }
            }
        });

    }

    private int isAlredyExists(String code) {
        for (int i = 0; i < obList.size(); i++) {
            if (obList.get(i).getPrdID().equals(code)) {
                return i;
            }
        }
        return -1;
    }

    private void calculateTotal() {
        double total = 0.00;
        for (CartTm tm : obList
        ) {
            total += tm.getTotal();
        }
        lblTotal.setText(String.valueOf(total));
    }


    private void loadNextOrderId() {
        try {
            String oID = OrdersModel.generateNextOID();
            lblOrderID.setText(oID);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) OrderID.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/DashBoard.fxml")))));

    }


    @FXML
    void btnBuyOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        String orderId = lblOrderID.getText();

        String custId = cmbCusID.getValue();
       // int qty = Integer.parseInt(txtOrderQTY.getText());
        String detail = txtPrdDESC.getText();
        //double price = Double.parseDouble(txtPrdUnitPrice.getText());
        Date date = Date.valueOf(LocalDate.now());

        ArrayList<OrderDetail> orderDetails = new ArrayList<>();
        for (int i = 0; i < tblOrderPlace.getItems().size(); i++) {
            String PrdID = String.valueOf(ColPrdID.getCellData(i));
            double unitPrice = Double.parseDouble(String.valueOf(ColUnitPrice.getCellData(i)));
            int orderQuantity = Integer.parseInt(String.valueOf(ColQTY.getCellData(i)));

            OrderDetail orderDetail = new OrderDetail(orderId,PrdID,unitPrice,orderQuantity);
            orderDetails.add(orderDetail);
        }
        Orders orders = new Orders(orderId,custId,detail,date,orderDetails);

        boolean placeOrder = PlaceOrderModel.placeOrder(orders);

        if (placeOrder){
            if (placeOrder) {
                new Alert(Alert.AlertType.CONFIRMATION, "Order Succssefully!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something happened!").show();
            }
        }

    }

    public void cmbCusID(ActionEvent actionEvent) {
    }

    public void CmbPrdIDOnAction(ActionEvent actionEvent) {
    }
}