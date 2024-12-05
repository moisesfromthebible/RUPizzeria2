package com.example.rupizzeria2.model;

import java.util.ArrayList;

/**
 * BBQChicken Pizza class
 *
 * @author Moises Cespedes Moreno, Binoy Patel
 */
public class BBQChicken extends Pizza{

    /**
     * BBQChicken pizza constructor
     *
     * @param toppings Arraylist of toppings
     * @param crust Crust type
     * @param size Size
     */
    public BBQChicken(ArrayList<Topping> toppings, Crust crust, Size size) {
        super(toppings, crust, size);
    }

    /**
     * Method to return teh price of the pizza.
     *
     * @return price (double)
     */
    @Override
    public double price() {
        return switch (this.getSize()){
            case SMALL -> 14.99;
            case MEDIUM -> 16.99;
            case LARGE -> 19.99;
        };
    }

    /**
     * toString method for BBQChicken
     *
     * @return String with pizza style
     */
    @Override
    public String toString(){
        if (getCrust() == Crust.PAN){
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
        if (getCrust() == Crust.PAN){
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
    public String getName() { return "BBQChicken"; }

}
