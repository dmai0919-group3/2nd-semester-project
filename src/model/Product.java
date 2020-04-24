package model;

public class Product {

    private int id;
    private String name;
    private int weight;
    private double price;

    public Product(int id, String name, int weight, Double price)
    {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.price = price;
    }

    public Product(String name, int weight, Double price)
    {
        this.name = name;
        this.weight = weight;
        this.price = price;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public int getWeight(){
        return weight;
    }

    public double getPrice(){
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}

