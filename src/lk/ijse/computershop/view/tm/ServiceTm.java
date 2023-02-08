package lk.ijse.computershop.view.tm;

public class ServiceTm {

    private String serID;
    private String emID;
    private String descripion;
    private Double price;

    public ServiceTm() {
    }

    public ServiceTm(String serID, String emID, String descripion, Double price) {
        this.serID = serID;
        this.emID = emID;
        this.descripion = descripion;
        this.price = price;
    }

    public String getSerID() {
        return serID;
    }

    public void setSerID(String serID) {
        this.serID = serID;
    }

    public String getEmID() {
        return emID;
    }

    public void setEmID(String emID) {
        this.emID = emID;
    }

    public String getDescripion() {
        return descripion;
    }

    public void setDescripion(String descripion) {
        this.descripion = descripion;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ServiceTm{" +
                "serID='" + serID + '\'' +
                ", emID='" + emID + '\'' +
                ", descripion='" + descripion + '\'' +
                ", price=" + price +
                '}';
    }
}