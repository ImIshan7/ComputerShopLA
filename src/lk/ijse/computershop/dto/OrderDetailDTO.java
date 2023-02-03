package lk.ijse.computershop.dto;

public class OrderDetailDTO {

    private String OrderID;
    private String PrdID;
    private double Price;
    private int QTY;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String orderID, String prdID, double price, int QTY) {
        OrderID = orderID;
        PrdID = prdID;
        Price = price;
        this.QTY = QTY;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getPrdID() {
        return PrdID;
    }

    public void setPrdID(String prdID) {
        PrdID = prdID;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public int getQTY() {
        return QTY;
    }

    public void setQTY(int QTY) {
        this.QTY = QTY;
    }

    @Override
    public String toString() {
        return "OrderDetailDTO{" +
                "OrderID='" + OrderID + '\'' +
                ", PrdID='" + PrdID + '\'' +
                ", Price=" + Price +
                ", QTY=" + QTY +
                '}';
    }
}
