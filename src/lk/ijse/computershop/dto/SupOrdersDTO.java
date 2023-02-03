package lk.ijse.computershop.dto;

import java.sql.Date;

public class SupOrdersDTO {
    private String SupOrderID;
    private String SupID;
    private java.sql.Date Date;

    public SupOrdersDTO() {
    }

    public SupOrdersDTO(String supOrderID, String supID, java.sql.Date date) {
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
        return "SupOrdersDTO{" +
                "SupOrderID='" + SupOrderID + '\'' +
                ", SupID='" + SupID + '\'' +
                ", Date=" + Date +
                '}';
    }
}
