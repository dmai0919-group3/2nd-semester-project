package model;

public class StoreStockReportItem {

    private int quantity;
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public StoreStockReportItem(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public int getAmount(){
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

