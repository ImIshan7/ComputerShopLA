package lk.ijse.computershop.to;

public class OrderDetail {
    private String OrderID;
    private String PrdID;
    private double Price;
    private int QTY;

    public OrderDetail() {
    }

    public OrderDetail(String orderID, String prdID, double price, int QTY) {
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

    public void setPrice(int price) {
        Price = price;
    }

    public double getQTY() {
        return QTY;
    }

    public void setQTY(int QTY) {
        this.QTY = QTY;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "OrderID='" + OrderID + '\'' +
                ", PrdID='" + PrdID + '\'' +
                ", Price=" + Price +
                ", QTY=" + QTY +
                '}';
    }
}
