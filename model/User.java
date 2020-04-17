package model;

public class User {

    private int id;
    private String name;
    private String password;
    private String email ;

    public User(int id, String name, String password, String email)
    {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
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

}

