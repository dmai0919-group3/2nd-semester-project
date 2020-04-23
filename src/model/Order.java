package model;

import java.sql.Date;
import  java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Order {

    private int id;
	private Date date;
    private List<OrderStatus> status;
    private double price;
    private Warehouse warehouse;
    /**
     * Status is set based on newest order change
     * This field is not stored in database
     */
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
    
    public Order(int i, Date date, double price, Warehouse warehouse, Store store, List<OrderItem> items, List<OrderStatus> status) {
		super();
		this.id = id;
		this.date = date;
		this.price = price;
		this.warehouse = warehouse;
		this.store = store;
		this.items = items;
		this.status = status;
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


