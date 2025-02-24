package com.example.rupizzeria2.model;

import androidx.annotation.NonNull;

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
    @NonNull
    @Override
    public String toString(){
        if (getCrust() == Crust.DEEP_DISH){
            return "Chicago Style " + super.toString();
        }else{
            return "NY Style " + super.toString();
        }
    }

    /**
     * Get Style method
     *
     * @return String representing style
     */
    public String getStyle(){
        if (getCrust() == Crust.DEEP_DISH){
            return "Chicago Style ";
        }else{
            return "NY Style ";
        }
    }

    /**
     * Returns the name of the pizza
     *
     * @return String name of Pizza
     */
    public String getName() { return "Deluxe"; }

}
