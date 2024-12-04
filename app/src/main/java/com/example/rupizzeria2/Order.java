package com.example.rupizzeria2;

import com.example.rupizzeria2.model.Pizza;

import java.util.ArrayList;

/**
 * Class to represent orders (Singleton pattern).
 *
 * @author Moises Cespedes Moreno, Binoy Patel
 */
public class Order {

    /** Total number of pizzas */
    private int number;
    /** Pizzas in order */
    private ArrayList<Pizza> pizzas;
    /** Instance of Order */
    private static Order instance;

    private Order() {
        this.number = 0;
        this.pizzas = new ArrayList<>();
    }

    /**
     * Public static method to provide access to the single instance of Order
     *
     * @return Singleton instance of Order
     */
    public static Order getInstance() {
        if (instance == null) {
            instance = new Order();
        }
        return instance;
    }

    /**
     * Adds pizza to order
     *
     * @param pizza Pizza to be added
     */
    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
        number++;
    }

    /**
     * Removes pizza from order
     *
     * @param pizza Pizza to be removed
     */
    public void removePizza(Pizza pizza) {
        if (number <= 0) return;
        pizzas.remove(pizza);
        number--;
    }

    /**
     * Getter method for pizzas
     *
     * @return List of pizzas
     */
    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    /**
     * Clears the order
     */
    public void clear() { this.pizzas = new ArrayList<>(); }

    /**
     * Creates a new Order
     */
    public void newOrder() {
        this.pizzas = new ArrayList<>();
        this.number++;
    }
}
