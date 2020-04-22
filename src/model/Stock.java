package model;

public class Stock {
    private int quantity;
    private Warehouse warehouse;

    public Stock(int quantity, Warehouse warehouse) {
        this.quantity = quantity;
        this.warehouse = warehouse;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}
