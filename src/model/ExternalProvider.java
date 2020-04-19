package model;

public class ExternalProvider {

    private int id;
    private String name;
    private String email;
    private boolean available;
    private Address address;

    public ExternalProvider(int id, String name, String email, boolean available, Address address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.available = available;
        this.address = address;
    }


    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public boolean getAvailable(){
        return available;
    }

    public Address getAddress(){
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    public void setAddress(Address address) {
        this.address = address;
    }
}

