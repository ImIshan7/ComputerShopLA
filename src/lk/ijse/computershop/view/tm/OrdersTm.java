package lk.ijse.computershop.view.tm;

public class OrdersTm {
    private String OrderID;
    private String CusID;
    private String Description;
    private String Date;

    public OrdersTm(String orderID, String cusID, String description, String date) {
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

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    @Override
    public String toString() {
        return "OrdersTm{" +
                "OrderID='" + OrderID + '\'' +
                ", CusID='" + CusID + '\'' +
                ", Description='" + Description + '\'' +
                ", Date='" + Date + '\'' +
                '}';
    }
}
