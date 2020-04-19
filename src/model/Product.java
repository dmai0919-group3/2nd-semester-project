package model;

import java.util.LinkedList;
import java.util.List;

public class Product {

    private int id;
    private String name;
    private int weight;
    private double price;
    private int minQuantity;
    private List<Stock> stock;

    public Product(int id, String name, int weight, Double price, int minQuantity)
    {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.minQuantity = minQuantity;
        stock = new LinkedList<Stock>();
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

    public int getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }

    public void addStock(Stock stock) {
        this.stock.add(stock);
    }

    public List<Stock> getStock() {
        return stock;
    }
}

