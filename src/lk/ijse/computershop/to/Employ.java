package lk.ijse.computershop.to;

public class Employ {

     private String EMID;
     private String Name;
     private String Address;
     private String Contact;

     public Employ(String s, String name, String address){

     }

    public Employ(String EMID, String name, String address, String contact) {
        this.EMID = EMID;
        Name = name;
        Address = address;
        Contact = contact;
    }

    public String getEMID() {
        return EMID;
    }

    public void setEMID(String EMID) {
        this.EMID = EMID;
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
        return "Employ{" +
                "EMID='" + EMID + '\'' +
                ", Name='" + Name + '\'' +
                ", Address='" + Address + '\'' +
                ", Contact='" + Contact + '\'' +
                '}';
    }
}
