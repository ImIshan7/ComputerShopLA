package lk.ijse.computershop.view.tm;

public class OrderDetailTm {

    private String OrderID;
    private String PrdID;
    private int Price;
    private double QTY;

    public OrderDetailTm() {
    }

    public OrderDetailTm(String orderID, String prdID, int price, double QTY) {
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

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public double getQTY() {
        return QTY;
    }

    public void setQTY(double QTY) {
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
