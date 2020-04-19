package model;

import  java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Order {

    private int id;
    private LocalDate date_created;
    private LocalDate date_delivered;
    private Status status;
    private double price;
    private Warehouse warehouse;
    private Store store;
    private List<OrderItem> orderItems;

    public Order(int id, LocalDate date_created, LocalDate date_delivered, String status, double price, Warehouse warehouse, Store store){
        this.id = id;
        this.date_created = date_created;
        this.date_delivered = date_delivered;
        this.status = Status.PENDING;
        this.price = price;
        this.warehouse = warehouse;
        this.store = store;
        orderItems = new LinkedList<>();
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

    public Status getStatus(){
        return status;
    }

    public double getPrice(){
        return price;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public void setDate_created(LocalDate date_created) {
        this.date_created = date_created;
    }

    public void setDate_delivered(LocalDate date_delivered) {
        this.date_delivered = date_delivered;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

}


