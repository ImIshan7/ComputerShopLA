package lk.ijse.computershop.entity;

import java.sql.Date;

public class SupOrders {
    private String SupOrderID;
    private String SupID;
    private java.sql.Date Date;

    public SupOrders() {
    }

    public SupOrders(String supOrderID, String supID, java.sql.Date date) {
        SupOrderID = supOrderID;
        SupID = supID;
        Date = date;
    }

    public String getSupOrderID() {
        return SupOrderID;
    }

    public void setSupOrderID(String supOrderID) {
        SupOrderID = supOrderID;
    }

    public String getSupID() {
        return SupID;
    }

    public void setSupID(String supID) {
        SupID = supID;
    }

    public java.sql.Date getDate() {
        return Date;
    }

    public void setDate(java.sql.Date date) {
        Date = date;
    }

    @Override
    public String toString() {
        return "SupOrders{" +
                "SupOrderID='" + SupOrderID + '\'' +
                ", SupID='" + SupID + '\'' +
                ", Date=" + Date +
                '}';
    }
}
