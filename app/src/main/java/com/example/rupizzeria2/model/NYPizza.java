package com.example.rupizzeria2.model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Creates a New York style pizza
 *
 * @author Moises Cespedes Moreno, Binoy Patel
 */
public class NYPizza implements PizzaFactory{

    /**
     * Creates a New York style deluxe pizza
     *
     * @return New York style deluxe pizza
     */
    @Override
    public Pizza createDeluxe() {
        ArrayList<Topping> toppings = new ArrayList<>(Arrays.asList(Topping.SAUSAGE,
                Topping.PEPPERONI,
                Topping.GREEN_PEPPER,
                Topping.ONION,
                Topping.MUSHROOM));
        return new Deluxe(toppings, Crust.BROOKLYN, null);
    }

    /**x
     * Creates a New York style meatzza pizza
     *
     * @return New York style meatzza pizza
     */
    @Override
    public Pizza createMeatzza() {
        ArrayList<Topping> toppings = new ArrayList<>(Arrays.asList(Topping.SAUSAGE,
                Topping.PEPPERONI,
                Topping.BEEF,
                Topping.HAM));
        return new Meatzza(toppings, Crust.HAND_TOSSED, null);
    }

    /**
     * Creates a New York style BBQChicken pizza
     *
     * @return New York style BBQChicken pizza
     */
    @Override
    public Pizza createBBQChicken() {
        ArrayList<Topping> toppings = new ArrayList<>(Arrays.asList(Topping.BBQ_CHICKEN,
                Topping.GREEN_PEPPER,
                Topping.PROVOLONE,
                Topping.CHEDDAR
        ));
        return new BBQChicken(toppings, Crust.THIN, null);
    }

    /**
     * Creates a New York style buildYourOwn pizza
     *
     * @return New York style buildYourOwn pizza
     */
    @Override
    public Pizza createBuildYourOwn() {
        ArrayList<Topping> toppings = new ArrayList<>();
        return new BuildYourOwn(toppings, Crust.HAND_TOSSED, null);
    }
}
