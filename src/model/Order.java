package model;

import  java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Order {

    private int id;
	private LocalDate date;
    private double price;
    private Warehouse warehouse;
    private Store store;
    private List<OrderItem> orderItems;
    private List<OrderChange> orderChanges;
    
    public Order() {
    	
    }
    
    public Order(LocalDate date, double price, Warehouse warehouse, Store store, List<OrderItem> orderItems, List<OrderChange> orderChanges) {
		super();
		this.date = date;
		this.price = price;
		this.warehouse = warehouse;
		this.store = store;
		this.orderItems = orderItems;
		this.orderChanges = orderChanges;
	}



	public Order(int id, LocalDate date, double price, Warehouse warehouse, Store store, List<OrderItem> orderItems, List<OrderChange> orderChanges) {
		super();
		this.id = id;
		this.date = date;
		this.price = price;
		this.warehouse = warehouse;
		this.store = store;
		this.orderItems = orderItems;
		this.orderChanges = orderChanges;
	}
    
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
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
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public List<OrderChange> getOrderChanges() {
		return orderChanges;
	}
	public void setOrderChanges(List<OrderChange> orderChanges) {
		this.orderChanges = orderChanges;
	}

}


