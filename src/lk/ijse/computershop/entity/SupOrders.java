package lk.ijse.computershop.entity;

import java.util.ArrayList;

public class SupOrders {

    private String SupOrderID;
    private String SupID;
    private java.sql.Date Date;

    private ArrayList<SupOrderDetail> SupOrderDetails;

    public SupOrders(String supOrderID, String supID, java.sql.Date date, ArrayList<SupOrderDetail> supOrderDetails) {
        SupOrderID = supOrderID;
        SupID = supID;
        Date = date;
        SupOrderDetails = supOrderDetails;
    }

    public SupOrders() {

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

    public ArrayList<SupOrderDetail> getSupOrderDetails() {
        return SupOrderDetails;
    }

    @Override
    public String toString() {
        return "SupOrders{" +
                "SupOrderID='" + SupOrderID + '\'' +
                ", SupID='" + SupID + '\'' +
                ", Date=" + Date +
                ", SupOrderDetails=" + SupOrderDetails +
                '}';
    }
}