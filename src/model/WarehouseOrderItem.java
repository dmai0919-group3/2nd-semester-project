package model;

public class WarehouseOrderItem {

    private int quantity;
    private Status status;
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public WarehouseOrderItem(int quantity, Status status, Product product)
    {
        this.quantity = quantity;
        this.status = status;
        this.product = product;
    }

    public int getQuantity(){
        return quantity;
    }

    public Status getStatus(){
        return status;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
