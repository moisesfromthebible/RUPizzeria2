package com.example.rupizzeria2.model;

import androidx.annotation.NonNull;

import java.util.ArrayList;

/**
 * Nested class to represent an individual order.
 *
 * @author Moises Cespedes Moreno, Binoy Patel
 */
public class Order {

    /** List of pizzas in the order */
    private ArrayList<Pizza> pizzas;
    /** Order number */
    private int orderNumber;

    /**
     * Order constructor.
     *
     * @param orderNumber Unique order number
     */
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

    /**
     * toString() method for order
     *
     * @return String representation for order
     */
    @NonNull
    @Override
    public String toString(){
        return String.format("Order Number: %d", orderNumber);
    }

    /**
     * Returns subtotal of order
     *
     * @return Subtotal
     */
    public double getSubtotal(){
        double subtotal = 0;
        for (Pizza pizza : pizzas){
            subtotal += pizza.price();
        }
        return subtotal;
    }
}