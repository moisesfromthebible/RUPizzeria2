package com.example.rupizzeria2.model;

import java.util.ArrayList;

/**
 * Class to represent and manage orders (Singleton pattern).
 */
public class OrderManager {

    /** List of all orders */
    private final ArrayList<Order> orders;
    /** Singleton instance of OrderManager */
    private static OrderManager instance;

    private static int currentOrder = 0;

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
     *
     * @return The newly created order
     */
    public Order createOrder() {
        Order newOrder = new Order(currentOrder++);
        orders.add(newOrder);
        return newOrder;
    }

    /**
     * Removes an order from the manager.
     *
     * @param order Order to be removed
     */
    public void removeOrder(Order order) {
        orders.remove(order);
    }

    /**
     * Retrieves a list of all orders.
     *
     * @return List of all orders
     */
    public ArrayList<Order> getOrders() {
        return orders;
    }

    /**
     * Clears all orders from the manager.
     */
    public void clearAllOrders() {
        orders.clear();
    }

    public Order getCurrOrder(){
        return orders.get(currentOrder - 1);
    }

    /**
     * Nested class to represent an individual order.
     */
    public class Order {

        /** List of pizzas in the order */
        private ArrayList<Pizza> pizzas;

        private int orderNumber;

        public Order(int orderNumber) {
            this.pizzas = new ArrayList<>();
            this.orderNumber = orderNumber;
        }

        /**
         * Adds a pizza to the order.
         *
         * @param pizza Pizza to be added
         */
        public void addPizza(Pizza pizza) {
            pizzas.add(pizza);
        }

        /**
         * Removes a pizza from the order.
         *
         * @param pizza Pizza to be removed
         */
        public void removePizza(Pizza pizza) {
            pizzas.remove(pizza);
        }

        /**
         * Retrieves the list of pizzas in the order.
         *
         * @return List of pizzas
         */
        public ArrayList<Pizza> getPizzas() {
            return pizzas;
        }

        /**
         * Clears all pizzas from the order.
         */
        public void clear() {
            pizzas.clear();
        }
    }
}
