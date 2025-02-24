package com.example.rupizzeria2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to represent and manage orders (Singleton pattern).
 *
 * @author Moises Cespedes Manuel, Binoy Patel
 */
public class OrderManager {

    /** List of all orders */
    private final ArrayList<Order> orders;

    /** Singleton instance of OrderManager */
    private static OrderManager instance;

    /** Current order number */
    private static int currentOrder = 1;

    /** Size of list */
    private static int size = 0;

    /**
     * Private constructor for orderManager
     */
    private OrderManager() {
        this.orders = new ArrayList<>();
    }

    /**
     * Public static method to provide access to the single instance of OrderManager
     *
     * @return Singleton instance of OrderManager
     */
    public static OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
            instance.createOrder();
        }
        return instance;
    }

    /**
     * Creates a new order and adds it to the list.
     */
    public void createOrder() {
        Order newOrder = new Order(currentOrder++);
        orders.add(newOrder);
        size++;
    }

    /**
     * Removes an order from the manager.
     *
     * @param order Order to be removed
     */
    public void removeOrder(Order order) {
        orders.remove(order);
        size--;
    }

    /**
     * Retrieves a list of all orders.
     *
     * @return List of all orders
     */
    public List<Order> getOrders() {
        return orders.subList(0, size-1);
    }

    /**
     * Retrieves current order
     *
     * @return most recent order
     */
    public Order getCurrOrder(){
        return orders.get(size-1);
    }
}
