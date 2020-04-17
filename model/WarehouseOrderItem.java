package model;

public class WarehouseOrderItem {

    private int quantity;
    private String status;

    public WarehouseOrderItem(int quantity, String status)
    {
        this.quantity = quantity;
        this.status = status;
    }

    public int getQuantity(){
        return quantity;
    }

    public String getStatus(){
        return status;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
