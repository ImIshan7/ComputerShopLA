package lk.ijse.computershop.view.tm;

public class ProductTm {

    private String prdID;
    private String name;
    private Double unit_Price;
    private String description;
    private int qty;

    public ProductTm() {
    }

    public ProductTm(String prdID, String name, Double unit_Price, String description, int qty) {
        this.prdID = prdID;
        this.name = name;
        this.unit_Price = unit_Price;
        this.description = description;
        this.qty = qty;
    }

    public String getPrdID() {
        return prdID;
    }

    public void setPrdID(String prdID) {
        this.prdID = prdID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getUnit_Price() {
        return unit_Price;
    }

    public void setUnit_Price(Double unit_Price) {
        this.unit_Price = unit_Price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "ProductTm{" +
                "prdID='" + prdID + '\'' +
                ", name='" + name + '\'' +
                ", unit_Price=" + unit_Price +
                ", description='" + description + '\'' +
                ", qty=" + qty +
                '}';
    }
}