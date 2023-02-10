package lk.ijse.computershop.dto;

import javafx.scene.control.Button;

public class OrderDetailDTO {

    private String orderID;
    private String prdID;
    private double price;
    private int qty;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String orderID, String prdID,double price,int qty) {
        this.orderID = orderID;
        this.prdID = prdID;
        this.price = price;
        this.qty = qty;
    }



    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getPrdID() {
        return prdID;
    }

    public void setPrdID(String prdID) {
        this.prdID = prdID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "OrderDetailDTO{" +
                "orderID='" + orderID + '\'' +
                ", prdID='" + prdID + '\'' +
                ", price=" + price +
                ", qty=" + qty +
                '}';
    }
}
