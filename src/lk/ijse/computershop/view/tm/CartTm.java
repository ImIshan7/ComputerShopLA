package lk.ijse.computershop.view.tm;

import javafx.scene.control.Button;

public class CartTm {

    private String PrdID;
    private String Description;
    private double Unit_Price;
    private int Order_QTY;
    private double Total;
    private Button Option;

    public CartTm() {

    }

    public CartTm(String prdID, String description, double unit_Price, int order_QTY, double total, Button option) {
        PrdID = prdID;
        Description = description;
        Unit_Price = unit_Price;
        Order_QTY = order_QTY;
        Total = total;
        Option = option;
    }

    public String getPrdID() {
        return PrdID;
    }

    public void setPrdID(String prdID) {
        PrdID = prdID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getUnit_Price() {
        return Unit_Price;
    }

    public void setUnit_Price(double unit_Price) {
        Unit_Price = unit_Price;
    }

    public int getOrder_QTY() {
        return Order_QTY;
    }

    public void setOrder_QTY(int order_QTY) {
        Order_QTY = order_QTY;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    public Button getOption() {
        return Option;
    }

    public void setOption(Button option) {
        Option = option;
    }

    @Override
    public String toString() {
        return "CartTm{" +
                "PrdID='" + PrdID + '\'' +
                ", Description='" + Description + '\'' +
                ", Unit_Price=" + Unit_Price +
                ", Order_QTY=" + Order_QTY +
                ", Total=" + Total +
                ", Option=" + Option +
                '}';
    }
}
