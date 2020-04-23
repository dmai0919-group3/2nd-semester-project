package controller;

import model.*;

public class OrderController {
    private Order order;

    public void createOrder(Store store, Warehouse warehouse) {
        this.order = new Order(store, warehouse);
    }

    public boolean addProduct(Product product, int amount) {
        // TODO: Add some order checks and throw exceptions
        // TODO: Change this for a case if order has the product already
        OrderItem orderItem = new OrderItem(product, amount);
        return order.addOrderItem(orderItem);
    }

    /**
     * Decrements amount of products in OrderItem
     * or removes OrderItem if amount is bigger than quantity
     *
     * @param product Product
     * @param amount int
     * @return boolean
     */
    public boolean removeProduct(Product product, int amount) {
        // TODO: Checks if order has the product
        // TODO: If amount is lower than real amount decrement only amount from orderItem
        return true;
    }

    /**
     * Removes whole orderItem
     *
     * @param orderItem OrderItem
     * @return boolean
     */
    public boolean removeOrderItem(OrderItem orderItem) {
        // TODO: Removal logic here
        return order.removeOrderItem(orderItem);
    }

    public boolean finishOrder() {
        // TODO: All final checks and DB save
        return true;
    }
}
