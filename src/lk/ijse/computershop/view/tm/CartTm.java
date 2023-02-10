package lk.ijse.computershop.view.tm;

import javafx.scene.control.Button;

public class CartTm {

    private String prdID;
    private String description;
    private double unit_Price;
    private int order_QTY;
    private double total;
    private Button option;

    public CartTm() {

    }

    public CartTm(String prdID, String description, double unit_Price, int order_QTY, double total, Button option) {
        this.prdID = prdID;
        this.description = description;
        this.unit_Price = unit_Price;
        this.order_QTY = order_QTY;
        this.total = total;
        this.option = option;
    }

    public String getPrdID() {
        return prdID;
    }

    public void setPrdID(String prdID) {
        this.prdID = prdID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnit_Price() {
        return unit_Price;
    }

    public void setUnit_Price(double unit_Price) {
        this.unit_Price = unit_Price;
    }

    public int getOrder_QTY() {
        return order_QTY;
    }

    public void setOrder_QTY(int order_QTY) {
        this.order_QTY = order_QTY;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Button getOption() {
        return option;
    }

    public void setOption(Button option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return "CartTm{" +
                "prdID='" + prdID + '\'' +
                ", description='" + description + '\'' +
                ", unit_Price=" + unit_Price +
                ", order_QTY=" + order_QTY +
                ", total=" + total +
                ", option=" + option +
                '}';
    }
}
