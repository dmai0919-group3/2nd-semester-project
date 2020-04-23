package model;

import java.sql.Date;
import  java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Order {

    private int id;
	private Date date;
    private double price;
    private Warehouse warehouse;
    private Store store;
    private List<OrderItem> items;
    private List<OrderStatus> status;
    
    public Order() {
    	
    }
    
    public Order(Date date, double price, Warehouse warehouse, Store store, List<OrderItem> items, List<OrderStatus> status) {
		super();
		this.date = date;
		this.price = price;
		this.warehouse = warehouse;
		this.store = store;
		this.items = items;
		this.status = status;
	}



	public Order(int id, Date date, double price, Warehouse warehouse, Store store, List<OrderItem> items, List<OrderStatus> list) {
		super();
		this.id = id;
		this.date = date;
		this.price = price;
		this.warehouse = warehouse;
		this.store = store;
		this.items = items;
		this.status = list;
	}
    
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
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
	public List<OrderItem> getItems() {
		return items;
	}
	public void setItems(List<OrderItem> orderItems) {
		this.items = orderItems;
	}
	public List<OrderStatus> getStatus() {
		return status;
	}
	public void setStatus(List<OrderStatus> orderStatus) {
		this.status = status;
	}

}


