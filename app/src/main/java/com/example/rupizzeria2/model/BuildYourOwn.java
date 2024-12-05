package com.example.rupizzeria2.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 * BuildYourOwn pizza object
 *
 * @author Moises Cespedes Moreno, Binoy Patel
 */
public class BuildYourOwn extends Pizza{

    /**
     * BuildYourOwn pizza constructor
     *
     * @param toppings Arraylist of toppings
     * @param crust Crust type
     * @param size Size
     */
    public BuildYourOwn(ArrayList<Topping> toppings, Crust crust, Size size) {
        super(toppings, crust, size);
    }

    /**
     * Method to return teh price of the pizza.
     *
     * @return price (double)
     */
    @Override
    public double price() {
        int numberOfToppings = this.getToppings().size();
        double basePrice = switch (this.getSize()) {
            case SMALL -> 8.99;
            case MEDIUM -> 10.99;
            case LARGE -> 12.99;
        };
        double totalPrice = basePrice + (TOPPING_PRICE * numberOfToppings);

        BigDecimal roundedPrice = new BigDecimal(totalPrice).setScale(2, RoundingMode.HALF_UP);
        return roundedPrice.doubleValue();
    }

    /**
     * Adds topping
     *
     * @param topping Topping to be added
     */
    @Override
    public void addTopping(Topping topping){
        this.getToppings().add(topping);
    }

    /**
     * Removes topping
     *
     * @param topping Topping to be removed
     */
    @Override
    public void removeTopping(Topping topping){
        this.getToppings().remove(topping);
    }

    /**
     * toString method for BuildYourOwn
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
    public String getName() { return "Build Your Own"; }

}
