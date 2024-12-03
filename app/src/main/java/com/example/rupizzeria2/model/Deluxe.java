package com.example.rupizzeria2.model;

import java.util.ArrayList;

/**
 * Deluxe pizza class
 *
 * @author Moises Cespedes Moreno, Binoy Patel
 */
public class Deluxe extends Pizza{

    /**
     * Deluxe pizza constructor
     *
     * @param toppings Arraylist of toppings
     * @param crust Crust type
     * @param size Size
     */
    public Deluxe(ArrayList<Topping> toppings, Crust crust, Size size) {
        super(toppings, crust, size);
    }

    /**
     * Method to return teh price of the pizza.
     *
     * @return price (double)
     */
    @Override
    public double price(){
        return switch (this.getSize()){
            case SMALL -> 16.99;
            case MEDIUM -> 18.99;
            case LARGE -> 20.99;
        };
    }

    /**
     * toString method for Deluxe
     *
     * @return String with pizza style
     */
    @Override
    public String toString(){
        if (getCrust() == Crust.DEEP_DISH){
            return "Chicago Style " + super.toString();
        }else{
            return "NY Style " + super.toString();
        }
    }
}
