package lk.ijse.computershop.to;

public class Supplier {

    private String SupID;
    private String Name;
    private String Address;
    private  String Brand;
    private Double Unit_Price;
    private int QTY;

   public  Supplier(String supID, String name, String address, String brand, String unitPrice, String qty){

   }

    public Supplier(String supID, String name, String address, String brand, Double unit_Price, int QTY) {
        SupID = supID;
        Name = name;
        Address = address;
        Brand = brand;
        Unit_Price = unit_Price;
        this.QTY = QTY;
    }

    public String getSupID() {
        return SupID;
    }

    public void setSupID(String supID) {
        SupID = supID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public Double getUnit_Price() {
        return Unit_Price;
    }

    public void setUnit_Price(Double unit_Price) {
        Unit_Price = unit_Price;
    }

    public int getQTY() {
        return QTY;
    }

    public void setQTY(int QTY) {
        this.QTY = QTY;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "SupID='" + SupID + '\'' +
                ", Name='" + Name + '\'' +
                ", Address='" + Address + '\'' +
                ", Brand='" + Brand + '\'' +
                ", Unit_Price=" + Unit_Price +
                ", QTY=" + QTY +
                '}';
    }
}