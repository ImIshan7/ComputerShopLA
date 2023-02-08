package lk.ijse.computershop.dto;


public class EmployDTO  {
    private String emID;
    private String name;
    private String address;
    private String contact;

    public EmployDTO() {
    }

    public EmployDTO(String eMID, String name, String address, String contact) {
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
        return "EmployDTO{" +
                "eMID='" + emID + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
