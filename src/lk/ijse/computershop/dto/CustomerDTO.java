package lk.ijse.computershop.dto;

public class CustomerDTO {
    private String ID;
    private String Name;
    private String Address;
    private String Contact;

    public CustomerDTO() {
    }

    public CustomerDTO(String ID, String name, String address, String contact) {
        this.ID = ID;
        Name = name;
        Address = address;
        Contact = contact;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "ID='" + ID + '\'' +
                ", Name='" + Name + '\'' +
                ", Address='" + Address + '\'' +
                ", Contact='" + Contact + '\'' +
                '}';
    }
}
