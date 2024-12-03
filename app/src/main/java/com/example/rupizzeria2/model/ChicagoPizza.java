package com.example.rupizzeria2.model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Creates a chicago style pizza
 *
 * @author Moises Cespedes Moreno, Binoy Patel
 */
public class ChicagoPizza implements PizzaFactory{

    /**
     * Creates a Chicago style deluxe pizza
     *
     * @return Chicago style deluxe pizza
     */
    @Override
    public Pizza createDeluxe() {
        ArrayList<Topping> toppings = new ArrayList<>(Arrays.asList(Topping.SAUSAGE,
                Topping.PEPPERONI,
                Topping.GREEN_PEPPER,
                Topping.ONION,
                Topping.MUSHROOM));
        return new Deluxe(toppings, Crust.DEEP_DISH, null);
    }

    /**
     * Creates a Chicago style meatzza pizza
     *
     * @return Chicago Style meatzza pizza
     */
    @Override
    public Pizza createMeatzza() {
        ArrayList<Topping> toppings = new ArrayList<>(Arrays.asList(Topping.SAUSAGE,
                Topping.PEPPERONI,
                Topping.BEEF,
                Topping.HAM));
        return new Meatzza(toppings, Crust.STUFFED, null);
    }

    /**
     * Creates a Chicago style BBQChicken pizza
     *
     * @return Chicago style BBQChicken pizza
     */
    @Override
    public Pizza createBBQChicken() {
        ArrayList<Topping> toppings = new ArrayList<>(Arrays.asList(Topping.BBQ_CHICKEN,
                Topping.GREEN_PEPPER,
                Topping.PROVOLONE,
                Topping.CHEDDAR
                ));
        return new BBQChicken(toppings, Crust.PAN, null);
    }

    /**
     * Creates a Chicago style buildYourOwn pizza
     *
     * @return Chicago style buildYourOwn pizza
     */
    @Override
    public Pizza createBuildYourOwn() {
        ArrayList<Topping> toppings = new ArrayList<>();
        return new BuildYourOwn(toppings, Crust.PAN, null);
    }
}
