package lk.ijse.computershop.to;

import java.sql.Date;
import java.util.ArrayList;

public class Orders {
    private String OrderID;
    private String CusID;
    private String Description;
    private java.sql.Date Date;

    private ArrayList<OrderDetail> orderDetails;

    public Orders() {
    }

    public Orders(String orderID, String cusID, String description, java.sql.Date date, ArrayList<OrderDetail> orderDetails) {
        OrderID = orderID;
        CusID = cusID;
        Description = description;
        Date = date;
        this.orderDetails = orderDetails;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getCusID() {
        return CusID;
    }

    public void setCusID(String cusID) {
        CusID = cusID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public java.sql.Date getDate() {
        return Date;
    }

    public void setDate(java.sql.Date date) {
        Date = date;
    }

    public ArrayList<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(ArrayList<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "OrderID='" + OrderID + '\'' +
                ", CusID='" + CusID + '\'' +
                ", Description='" + Description + '\'' +
                ", Date=" + Date +
                ", orderDetails=" + orderDetails +
                '}';
    }
}
