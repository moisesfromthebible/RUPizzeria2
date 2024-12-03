package com.example.rupizzeria2.model;

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
     * toString method for Meatzza
     *
     * @return String which adds pizza type
     */
    @Override
    public String toString(){
        if (getCrust() == Crust.STUFFED){
            return "Chicago Style " + super.toString();
        }else{
            return "NY Style " + super.toString();
        }
    }
}
