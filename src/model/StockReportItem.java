package model;

public class StockReportItem {

    private int quantity;
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public StockReportItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public int getAmount(){
        return quantity;
    }

    public void setAmount(int quantity) {
        this.quantity = quantity;
    }
}

