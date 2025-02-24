package com.example.rupizzeria2.model;

import androidx.annotation.NonNull;

import java.util.ArrayList;

/**
 * Meatzza pizza object class
 *
 * @author Moises Cespedes Moreno, Binoy Patel
 */
public class Meatzza extends Pizza{

    /**
     * Meatzza pizza constructor
     *
     * @param toppings Arraylist of toppings
     * @param crust Crust type
     * @param size Size
     */
    public Meatzza(ArrayList<Topping> toppings, Crust crust, Size size) {
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
            case SMALL -> 17.99;
            case MEDIUM -> 19.99;
            case LARGE -> 21.99;
        };
    }

    /**
     * Get Style method
     *
     * @return String representing style
     */
    public String getStyle(){
        if (getCrust() == Crust.STUFFED){
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
    public String getName() { return "Meatzza"; }

    /**
     * toString method for Meatzza
     *
     * @return String which adds pizza type
     */
    @NonNull
    @Override
    public String toString(){
        if (getCrust() == Crust.STUFFED){
            return "Chicago Style " + super.toString();
        }else{
            return "NY Style " + super.toString();
        }
    }
}
