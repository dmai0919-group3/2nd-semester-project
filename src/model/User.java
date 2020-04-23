package model;

public class User {

    private int id;
    private String name;
    private String password;
    private String email;

    private Address address;

    public User(int id, String name, String password, String email, Address address)
    {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.address = address;
    }


    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getPassword(){
        return password;
    }

    public String getEmail(){
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email ) {
        this.email  = email ;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

