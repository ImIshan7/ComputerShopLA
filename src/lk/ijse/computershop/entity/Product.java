package lk.ijse.computershop.entity;

public class Product {

    private String PrdID;
    private String Name;
    private Double Unit_Price;
    private String Description;
    private int QTY;

    public Product() {
    }

    public Product(String prdID, String name, Double unit_Price, String description, int QTY) {
        PrdID = prdID;
        Name = name;
        Unit_Price = unit_Price;
        Description = description;
        this.QTY = QTY;
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

    public int getQTY() {
        return QTY;
    }

    public void setQTY(int QTY) {
        this.QTY = QTY;
    }

    @Override
    public String toString() {
        return "Product{" +
                "PrdID='" + PrdID + '\'' +
                ", Name='" + Name + '\'' +
                ", Unit_Price=" + Unit_Price +
                ", Description='" + Description + '\'' +
                ", QTY=" + QTY +
                '}';
    }
}
