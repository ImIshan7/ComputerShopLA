package lk.ijse.computershop.view.tm;

public class EmployTm  {

    private String emID;
    private String name;
    private String address;
    private String contact;

    public EmployTm() {
    }

    public EmployTm(String eMID, String name, String address, String contact) {
        this.emID = eMID;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public String geteMID() {
        return emID;
    }

    public void seteMID(String eMID) {
        this.emID = eMID;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "EmployTm{" +
                "eMID='" + emID + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
