package model;

import java.time.LocalDate;

public class OrderStatus {	
	private int id;
	private Status status;
	private LocalDate date;
	private String note;
	private int orderID;
	
	public OrderStatus(int id, Status status, LocalDate date, String note, int orderID) {
		super();
		this.id = id;
		this.status = status;
		this.date = date;
		this.note = note;
		this.orderID = orderID;
	}

	public OrderStatus(Status status, LocalDate date, String note, int orderID) {
		super();
		this.status = status;
		this.date = date;
		this.note = note;
		this.orderID = orderID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
}
