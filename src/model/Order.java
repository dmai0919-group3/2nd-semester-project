package model;

import java.sql.Date;
import  java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Order {

    private int id;
    private LocalDate date;
    /**
     * Status is set based on newest order change
     * This field is not stored in order table
     */
    private Status status;
    private double price;
    private Warehouse warehouse;
    private Store store;
    private List<OrderItem> items;

    public Order(Store store, Warehouse warehouse) {
        this.store = store;
        this.warehouse = warehouse;
        items = new LinkedList<>();
    }

    public Order(int id, Date date, List<OrderStatus> status, double price, Warehouse warehouse, Store store){
        this.id = id;
        this.date = date;
        this.status = status;
        this.price = price;
        this.warehouse = warehouse;
        this.store = store;
        items = new LinkedList<>();
    }

    public int getId(){
        return id;
    }

    public LocalDate getDate(){
        return date;
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

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean addOrderItem(OrderItem orderItem) {
        return orderItems.add(orderItem);
    }

    public boolean removeOrderItem(OrderItem orderItem) {
        return orderItems.remove(orderItem);
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

}


