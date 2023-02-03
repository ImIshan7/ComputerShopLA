package lk.ijse.computershop.dto;

public class ServiceDTO {
    private String SerID;
    private String EMID;
    private String Descripion;
    private Double Price;

    public ServiceDTO(String text, String txtEmIDText, String txtDESCText, String txtPriceText) {
    }

    public ServiceDTO(String serID, String EMID, String descripion, Double price) {
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
        return "ServiceDTO{" +
                "SerID='" + SerID + '\'' +
                ", EMID='" + EMID + '\'' +
                ", Descripion='" + Descripion + '\'' +
                ", Price=" + Price +
                '}';
    }
}