package lk.ijse.computershop.view.tm;

public class ServiceTm {

    private String SerID;
    private String EMID;
    private String Descripion;
    private Double Price;

    public ServiceTm(String serID, String EMID, String descripion, Double price) {
        SerID = serID;
        this.EMID = EMID;
        Descripion = descripion;
        Price = price;
    }

    public String getSerID() {
        return SerID;
    }

    public void setSerID(String serID) {
        SerID = serID;
    }

    public String getEMID() {
        return EMID;
    }

    public void setEMID(String EMID) {
        this.EMID = EMID;
    }

    public String getDescripion() {
        return Descripion;
    }

    public void setDescripion(String descripion) {
        Descripion = descripion;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    @Override
    public String toString() {
        return "ServiceTm{" +
                "SerID='" + SerID + '\'' +
                ", EMID='" + EMID + '\'' +
                ", Descripion='" + Descripion + '\'' +
                ", Price=" + Price +
                '}';
    }
}