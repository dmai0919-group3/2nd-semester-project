package model;

public class Stock {
    private int quantity;
    private Warehouse warehouse;

    public Stock(int quantity, Warehouse warehouse) {
        this.quantity = quantity;
        this.warehouse = warehouse;
    }
}
