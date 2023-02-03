package lk.ijse.computershop.dto;

public class SupOrderDetailDTO {

    private String PrdID;
    private String  SupOrderID;
    private int QTY;
    private String Descripion;
    private double UnitPrice;


    public SupOrderDetailDTO() {
    }

    public SupOrderDetailDTO(String prdID, String supOrderID, int QTY, String descripion, double unitPrice) {
        PrdID = prdID;
        SupOrderID = supOrderID;
        this.QTY = QTY;
        Descripion = descripion;
        UnitPrice = unitPrice;
    }

    public String getPrdID() {
        return PrdID;
    }

    public void setPrdID(String prdID) {
        PrdID = prdID;
    }

    public String getSupOrderID() {
        return SupOrderID;
    }

    public void setSupOrderID(String supOrderID) {
        SupOrderID = supOrderID;
    }

    public int getQTY() {
        return QTY;
    }

    public void setQTY(int QTY) {
        this.QTY = QTY;
    }

    public String getDescripion() {
        return Descripion;
    }

    public void setDescripion(String descripion) {
        Descripion = descripion;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        UnitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "SupOrderDetailDTO{" +
                "PrdID='" + PrdID + '\'' +
                ", SupOrderID='" + SupOrderID + '\'' +
                ", QTY=" + QTY +
                ", Descripion='" + Descripion + '\'' +
                ", UnitPrice=" + UnitPrice +
                '}';
    }
}
