package lk.ijse.computershop.entity;

import java.util.ArrayList;

public class PlaceOrder {
    private String CusID;
    private String OrderID;
    private String Description;
    private ArrayList<CartDetail> orderdetails = new ArrayList<>();

    public PlaceOrder() {
    }

    public PlaceOrder(String cusID, String orderID, String description, ArrayList<CartDetail> orderdetails) {
        CusID = cusID;
        OrderID = orderID;
        Description = description;
        this.orderdetails = orderdetails;
    }

    public String getCusID() {
        return CusID;
    }

    public void setCusID(String cusID) {
        CusID = cusID;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public ArrayList<CartDetail> getOrderdetails() {
        return orderdetails;
    }

    public void setOrderdetails(ArrayList<CartDetail> orderdetails) {
        this.orderdetails = orderdetails;
    }

    @Override
    public String toString() {
        return "PlaceOrder{" +
                "CusID='" + CusID + '\'' +
                ", OrderID='" + OrderID + '\'' +
                ", Description='" + Description + '\'' +
                ", orderdetails=" + orderdetails +
                '}';
    }
}
