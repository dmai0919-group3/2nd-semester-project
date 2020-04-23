package model;

import java.time.LocalDate;
import java.util.List;

public class OrderRevision {
    private int id;
    private LocalDate date;
    private Status status;
    private String note;
    private Double priceChange;
    private Order order;
    private List<OrderItem> itemsChanged;

    public OrderRevision(LocalDate date, Status status, String note, Double priceChange, Order order, List<OrderItem> itemsChanged) {
        this.date = date;
        this.status = status;
        this.note = note;
        this.priceChange = priceChange;
        this.order = order;
        this.itemsChanged = itemsChanged;
    }

    public OrderRevision(int id, LocalDate date, Status status, String note, Double priceChange, Order order, List<OrderItem> itemsChanged) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.note = note;
        this.priceChange = priceChange;
        this.order = order;
        this.itemsChanged = itemsChanged;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Double getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(Double priceChange) {
        this.priceChange = priceChange;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderItem> getItemsChanged() {
        return itemsChanged;
    }

    public void setItemsChanged(List<OrderItem> itemsChanged) {
        this.itemsChanged = itemsChanged;
    }
}
