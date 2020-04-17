package model;

import  java.time.LocalDate;

public class Order {

    private int id;
    private LocalDate date_created;
    private LocalDate date_delivered;
    private String status;
    private double price;

    public Order(int id, LocalDate date_created, LocalDate date_delivered, String status, double price){
        this.id = id;
        this.date_created = date_created;
        this.date_delivered = date_delivered;
        this.status = status;
        this.price = price;
    }


    public int getId(){
        return id;
    }

    public LocalDate getDate_created(){
        return date_created;
    }

    public LocalDate getDate_delivered(){
        return date_delivered;
    }

    public String getStatus(){
        return status;
    }

    public double getPrice(){
        return price;
    }


    public void setDate_created(LocalDate date_created) {
        this.date_created = date_created;
    }

    public void setDate_delivered(LocalDate date_delivered) {
        this.date_delivered = date_delivered;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}


