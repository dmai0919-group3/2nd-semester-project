package model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class WarehouseOrder {

    private int id;
    private LocalDate date;
    private String status;
    private Warehouse warehouse;
    private List<WarehouseOrderItem> items;

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public WarehouseOrder(int id, LocalDate date, String status, Warehouse warehouse)
    {
        this.id = id;
        this.date = date;
        this.status = status;
        this.warehouse = warehouse;
        items = new LinkedList<>();
    }

    public int getId(){
        return id;
    }

    public LocalDate getDate(){
        return date;
    }

    public String getStatus(){
        return status;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addItem(WarehouseOrderItem item) {
        items.add(item);
    }
    public List<WarehouseOrderItem> getItems() {
        return items;
    }

}

