package lk.ijse.computershop.dto;

public class SupplierDTO {

    private String supid;
    private String name;
    private String address;
    private  String brand;
    private Double unit_Price;
    private int qty;

    public SupplierDTO() {
    }

    public SupplierDTO(String supid, String name, String address, String brand) {
        this.supid = supid;
        this.name = name;
        this.address = address;
        this.brand = brand;
        this.unit_Price = unit_Price;
        this.qty = qty;
    }

    public String getSupid() {
        return supid;
    }

    public void setSupid(String supid) {
        this.supid = supid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getUnit_Price() {
        return unit_Price;
    }

    public void setUnit_Price(Double unit_Price) {
        this.unit_Price = unit_Price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "SupplierDTO{" +
                "supid='" + supid + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", brand='" + brand + '\'' +
                ", unit_Price=" + unit_Price +
                ", qty=" + qty +
                '}';
    }
}
