package lk.ijse.computershop.view.tm;

public class SupCartTm {

    private String SupID;
    private String PrdID;
    private int QTY;
    private String Description;
    private double Unit_Price;
    private double Total;

    public SupCartTm(String value, String text, double unitPrice, int qty, double total) {
    }

    public SupCartTm(String supID, String prdID, int QTY, String description, double unit_Price, double total) {
        SupID = supID;
        PrdID = prdID;
        this.QTY = QTY;
        Description = description;
        Unit_Price = unit_Price;
        Total = total;
    }

    public String getSupID() {
        return SupID;
    }

    public void setSupID(String supID) {
        SupID = supID;
    }

    public String getPrdID() {
        return PrdID;
    }

    public void setPrdID(String prdID) {
        PrdID = prdID;
    }

    public int getQTY() {
        return QTY;
    }

    public void setQTY(int QTY) {
        this.QTY = QTY;
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

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    @Override
    public String toString() {
        return "SupCartTm{" +
                "SupID='" + SupID + '\'' +
                ", PrdID='" + PrdID + '\'' +
                ", QTY=" + QTY +
                ", Description='" + Description + '\'' +
                ", Unit_Price=" + Unit_Price +
                ", Total=" + Total +
                '}';
    }
}
