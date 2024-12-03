package com.example.rupizzeria2.model;

import java.util.ArrayList;

/**
 * Pizza abstract class.
 *
 * @author Moises Cespedes Moreno, Binoy Patel
 */
public abstract class Pizza {

    /** List of toppings */
    private ArrayList<Topping> toppings;
    /** Type of crust */
    private Crust crust;
    /** Size of the pizza */
    private Size size;

    /** Price of each topping */
    protected static final double TOPPING_PRICE = 1.69;

    /**
     * Constructor method for pizza object
     *
     * @param toppings Arraylist of toppings
     * @param crust Crust type
     * @param size Size
     */
    public Pizza(ArrayList<Topping> toppings, Crust crust, Size size) {
        this.toppings = toppings;
        this.crust = crust;
        this.size = size;
    }

    /**
     * Method to return teh price of the pizza.
     *
     * @return price (double)
     */
    public abstract double price();

    /**
     * Getter method for toppings
     *
     * @return Topping arraylist
     */
    public ArrayList<Topping> getToppings(){return toppings;}

    /**
     * Getter method for crust
     *
     * @return Crust object
     */
    public Crust getCrust(){return crust;}

    /**
     * Getter method for size
     *
     * @return Size object
     */
    public Size getSize(){return size;}

    /**
     * Setter method for size
     *
     * @param size Size of pizza
     */
    public void setSize(Size size){this.size = size;}

    /**
     * Does nothing if executed on any pizza aside buildYourOwn
     *
     * @param topping Topping to be added
     */
    public void addTopping(Topping topping){}

    /**
     * Does nothing if executed on any pizza aside buildYourOwn
     *
     * @param topping Topping to be removed
     */
    public void removeTopping(Topping topping){}

    /**
     * ToString method for Pizza
     *
     * @return String representation of pizza
     */
    @Override
    public String toString(){
        return String.format("%s: [%s] [%s] [%s] Price: $%.2f",
                this.getClass().getSimpleName(),
                this.getSize(),
                this.getCrust(),
                getToppingsString(),
                this.price());
    }

    /**
     * Creates a String list of toppings
     *
     * @return String list
     */
    private String getToppingsString() {
        if (toppings.isEmpty()) {
            return "Toppings: None";
        }

        StringBuilder toppingsString = new StringBuilder("Toppings: ");
        for (Topping topping : toppings) {
            toppingsString.append(topping.toString()).append(", ");
        }

        return toppingsString.substring(0, toppingsString.length() - 2);
    }

}
