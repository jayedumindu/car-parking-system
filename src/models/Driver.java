package models;

public class Driver {
    private String NIC;
    private String Name;
    private String LN;
    private String Address;
    private String Contact;
    private boolean isOnShift = false;

    public Driver(String NIC, String name, String LN, String address, String contact, boolean shift) {
        this.NIC = NIC;
        Name = name;
        this.LN = LN;
        Address = address;
        Contact = contact;
        isOnShift = shift;
    }

    public Driver() {
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLN() {
        return LN;
    }

    public void setLN(String LN) {
        this.LN = LN;
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
        return "Driver{" +
                "NIC='" + NIC + '\'' +
                ", Name='" + Name + '\'' +
                ", LN='" + LN + '\'' +
                ", Address='" + Address + '\'' +
                ", Contact='" + Contact + '\'' +
                '}';
    }

    public boolean isOnShift() {
        return isOnShift;
    }

    public void setOnShift(boolean onShift) {
        isOnShift = onShift;
    }

}
