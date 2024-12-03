package com.example.rupizzeria2.model;

import java.util.ArrayList;

/**
 * Class to represent orders.
 *
 * @author Moises Cespedes Moreno, Binoy Patel
 */
public class Order {

    /** Total number of pizzas */
    private int number;
    /** Pizzas in order*/
    private ArrayList<Pizza> pizzas;

    /**
     * Constructor for order
     *
     * @param number Order number
     */
    public Order(int number){
        this.number = number;
        this.pizzas = new ArrayList<>();
    }

    /**
     * Adds pizza to order
     *
     * @param pizza Pizza to be added
     */
    public void addPizza(Pizza pizza){
        pizzas.add(pizza);
        number++;
    }

    /**
     * Removes pizza from order
     *
     * @param pizza Pizza to be removed
     */
    public void removePizza(Pizza pizza){
        if (number <= 0) return;
        pizzas.remove(pizza);
        number--;
    }

    /**
     * Getter method for pizzas
     *
     * @return List of pizzas
     */
    public ArrayList<Pizza> getPizzas(){return pizzas;}

    /**
     * Clears an order
     */
    public void clear(){this.pizzas = new ArrayList<>();}
}
