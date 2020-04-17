package model;

public class Stock {

    private int quantity;
    private int minQuantity ;

    public Stock(int quantity, int minQuantity)
    {
        this.quantity = quantity;
        this.minQuantity = minQuantity;
    }


    public int getQuantity(){
        return quantity;
    }

    public int getMinQuantity(){
        return minQuantity ;
    }
 

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setMinQuantity(int minQuantity ) {
        this.minQuantity  = minQuantity;
    }

}

