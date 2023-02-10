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
import lk.ijse.computershop.bo.custom.CustomerBO;
import lk.ijse.computershop.bo.custom.OrderPlaceBO;
import lk.ijse.computershop.bo.custom.ProductBO;
import lk.ijse.computershop.dto.CustomerDTO;
import lk.ijse.computershop.dto.OrderDetailDTO;
import lk.ijse.computershop.dto.OrdersDTO;
import lk.ijse.computershop.dto.ProductDTO;
import lk.ijse.computershop.model.OrdersModel;
import lk.ijse.computershop.model.PlaceOrderModel;
import lk.ijse.computershop.entity.OrderDetail;
import lk.ijse.computershop.entity.Orders;
import lk.ijse.computershop.view.tm.CartTm;
import lk.ijse.computershop.view.tm.OrdersTm;


import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class OrderPlaceController {


    public Label lblOrderID;
    public Label lbldate;

    public Label lbltime;
    public TableView<OrdersTm> tblOrderPlace;
    public TableColumn ColPrdID;
    public TableColumn ColDESC;
    public TableColumn ColUnitPrice;
    public TableColumn ColQTY;
    public TableColumn ColTotal;
    public TableColumn<OrdersTm,Button> ColOption;
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

    private String orderId ;
    String selectedID;

    OrderPlaceBO orderPlaceBO = (OrderPlaceBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.OP);
    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.CUSTOMER);
    ProductBO productBO = (ProductBO) BOFactory.getBoFactory().getBO(BOFactory.BoTypes.PRODUCT);

        static ObservableList<OrdersTm> obList = FXCollections.observableArrayList();
    public void initialize() {
        obList.clear();

        ColPrdID.setCellValueFactory(new PropertyValueFactory<>("prdID"));
        ColDESC.setCellValueFactory(new PropertyValueFactory<>("description"));
        ColUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unit_Price"));
        ColQTY.setCellValueFactory(new PropertyValueFactory<>("order_QTY"));
        ColTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        ColOption.setCellValueFactory(new PropertyValueFactory<OrdersTm,Button>("Button"));

        loadOrderDate();
        loadOrderTime();
      // loadNextOrderId();

        lblOrderID.setText(loadNextOID());
        loadCusIDs();
        loadPrdIDs();
        loadNextOID();


       /* cmbCusID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                ();
            }
        });*/

        cmbPrdID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
               // setProducts();
            }
        });


    }



   /* private void setProducts() {
        try {
           for (Product product : ProductController.getAllProduct()) {
                if (product.getPrdID().equals(cmbPrdID.getValue())) {
                    txtPrdName.setText(product.getName());
                   txtPrdDESC.setText(product.getDescription());
                    txtPrdUnitPrice.setText(String.valueOf(product.getUnit_Price()));
                    txtPrdQTY.setText(String.valueOf(product.getQTY()));
               }
           }


       } catch (Exception e) {
       }
    }*/


    private void loadOrderDate() {
        lbldate.setText(String.valueOf(LocalDate.now()));
    }

    private void loadOrderTime() {
        lbltime.setText(String.valueOf(LocalTime.now()));
    }


   // ObservableList<CartTm> obList = FXCollections.observableArrayList();

    public boolean saveOrder(String orderId, String cusId, String description, java.sql.Date date, List<OrderDetailDTO> orderDetailDTO) throws SQLException, ClassNotFoundException {
        OrdersDTO ordersDTO = new OrdersDTO(orderId,cusId,description,date,orderDetailDTO);
        return orderPlaceBO.orderPlace(ordersDTO);
    }
    @FXML
    void btnBuyOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

       /* try {
            boolean b = saveOrder(lblOrderID.getText(), Date.valueOf(LocalDate.now()), String.valueOf(cmbCusID.getValue()),
                    tblOrderPlace.getItems().stream().map(tm -> new OrderDetailDTO(
                            lblOrderID.getText() ,tm.getPrdID(),tm.getUnit_Price(),tm.getOrder_QTY())));

            if (b) {
                new Alert(Alert.AlertType.INFORMATION, "Order has been placed successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Order has not been placed successfully").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }*/

//        orderId = loadNextOID();
//        lblId.setText("Order Id: " + orderId);
        lblOrderID.setText(orderId);
//        cmbCID.getSelectionModel().clearSelection();
//        cmbIID.getSelectionModel().clearSelection();
        tblOrderPlace.getItems().clear();
        txtOrderQTY.clear();
        calculateTotal();

    }



    @FXML
    void btnAddOnAction(ActionEvent actionEvent) {

        String prdID = String.valueOf(cmbPrdID.getSelectionModel().getSelectedItem());
        String cusID = String.valueOf(cmbCusID.getSelectionModel().getSelectedItem());
        double unitPrice = Double.parseDouble(txtPrdUnitPrice.getText());
        int qty = Integer.parseInt((txtOrderQTY.getText()));
        double total = qty * unitPrice;
        Button button = new Button("Delete");

            OrdersTm ordersTm = new OrdersTm(prdID,cusID,unitPrice, qty, total, button);
        for (int i = 0; i < tblOrderPlace.getItems().size(); i++) {
            if (ColPrdID.getCellData(i).equals(String.valueOf(cmbPrdID.getSelectionModel().getSelectedItem()))) {
                int tempQty = obList.get(i).getOrder_QTY() + qty;

                double tot = unitPrice * tempQty;

                obList.get(i).setOrder_QTY(tempQty);

                obList.get(i).setTotal(tot);

                tblOrderPlace.refresh();

                generateTotal();

                return;
            }
        }

        obList.add(ordersTm);

        tblOrderPlace.setItems(obList);

        generateTotal();

        calculateTotal();
        cmbPrdID.requestFocus();

    }


    public void generateTotal(){
        double total = 0;
        for (int i = 0; i < tblOrderPlace.getItems().size(); i++) {
            total += Double.parseDouble(String.valueOf(ColTotal.getCellData(i)));

        }
        lblTotal.setText(String.valueOf(total));
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
        for (OrdersTm ordersTm: obList
        ) {
            total += ordersTm.getTotal();
        }
        lblTotal.setText(String.valueOf(total));
    }


   /* private void loadNextOrderId() {
        try {
            String oID = OrdersModel.generateNextOID();
            lblOrderID.setText(oID);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }*/

    private void loadPrdIDs() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
//            ArrayList<String> itemList = ItemModel.loadIIDs();
            ArrayList<String> itemList = productBO.loadPrdIDs();

            for (String iID : itemList) {
                observableList.add(iID);
            }
            cmbPrdID.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadCusIDs() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> cIDList = customerBO.loadCusIDs();

            for (String cID : cIDList) {
                observableList.add(cID);
            }
            cmbCusID.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String loadNextOID() {
        try {
//            String oID = OrderModel.generateNextOID();
//            String oID = purchaseOrderBO.generateNextOID();
            return orderPlaceBO.generateNextOID();
//            lblOID.setText(oID);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new order id").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "O001";
    }


    private void fillProductFields(ProductDTO productDTO) {

        txtPrdName.setText(productDTO.getName());
        txtPrdDESC.setText(productDTO.getDescription());
        txtPrdUnitPrice.setText(String.valueOf(productDTO.getUnit_Price()));
        txtPrdQTY.setText(String.valueOf(productDTO.getQty()));
        /*lblDesc.setText(itemDTO.getDesc());
        lblUnitPrice.setText(String.valueOf(itemDTO.getUnitPrice()));
        lblQtyOnHand.setText(String.valueOf(itemDTO.getQtyOnHand()));*/
    }




    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) OrderID.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/DashBoard.fxml")))));

    }


    public void cmbCusID(ActionEvent actionEvent) {
        String cusID = String.valueOf(cmbCusID.getValue());
        try {
            // search customer
//            customerBO.searchCustomer(cID);

//            CustomerDTO customerDTO = CustomerModel.search(cID);
            CustomerDTO customerDTO = customerBO.searchCustomer(cusID);
            fillCustomerFields(customerDTO);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void CmbPrdIDOnAction(ActionEvent actionEvent) {
        String PrdID = String.valueOf(cmbPrdID.getValue());
        try {
//            ItemDTO itemDTO = ItemModel.search(iID);
            ProductDTO productDTO = productBO.searchProduct(PrdID);
            System.out.println(productDTO);
            fillProductFields(productDTO);
            txtPrdQTY.requestFocus();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillCustomerFields(CustomerDTO customerDTO) {
        System.out.println(customerDTO.getName());
        txtCusName.setText(customerDTO.getName());
        txtCusAddress.setText(customerDTO.getAddress());
        txtCusContact.setText(customerDTO.getContact());
    }
}