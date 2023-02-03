package lk.ijse.computershop.dto;

import java.sql.Date;

public class OrdersDTO {

    private String OrderID;
    private String CusID;
    private String Description;
    private java.sql.Date Date;

    public OrdersDTO() {
    }

    public OrdersDTO(String orderID, String cusID, String description, java.sql.Date date) {
        OrderID = orderID;
        CusID = cusID;
        Description = description;
        Date = date;
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

    @Override
    public String toString() {
        return "OrdersDTO{" +
                "OrderID='" + OrderID + '\'' +
                ", CusID='" + CusID + '\'' +
                ", Description='" + Description + '\'' +
                ", Date=" + Date +
                '}';
    }
}
