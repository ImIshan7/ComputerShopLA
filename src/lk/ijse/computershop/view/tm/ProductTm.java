package lk.ijse.computershop.view.tm;

public class ProductTm {

    private String PrdID;
    private String Name;
    private Double Unit_Price;
    private String Description;
    private int Qty;

    public ProductTm() {
    }

    public ProductTm(String prdID, String name, Double unit_Price, String description, int qty) {
        PrdID = prdID;
        Name = name;
        Unit_Price = unit_Price;
        Description = description;
        Qty = qty;
    }

    public String getPrdID() {
        return PrdID;
    }

    public void setPrdID(String prdID) {
        PrdID = prdID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Double getUnit_Price() {
        return Unit_Price;
    }

    public void setUnit_Price(Double unit_Price) {
        Unit_Price = unit_Price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    @Override
    public String toString() {
        return "ProductTm{" +
                "PrdID='" + PrdID + '\'' +
                ", Name='" + Name + '\'' +
                ", Unit_Price=" + Unit_Price +
                ", Description='" + Description + '\'' +
                ", Qty=" + Qty +
                '}';
    }
}