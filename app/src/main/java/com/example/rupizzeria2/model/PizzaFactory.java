package com.example.rupizzeria2.model;

/**
 * Interface class to create pizzas
 *
 * @author Moises Cespedes, Binoy Patel
 */
public interface PizzaFactory {
    /**
     * Creates deluxe pizza
     *
     * @return Deluxe pizza
     */
    Pizza createDeluxe();

    /**
     * Creates meatzza pizza
     *
     * @return Meatzza pizza
     */
    Pizza createMeatzza();

    /**
     * Creates BBQChicken pizza
     *
     * @return BBQChicken pizza
     */
    Pizza createBBQChicken();

    /**
     * Creates buildYourOwn pizza
     *
     * @return BuildYourOwn pizza
     */
    Pizza createBuildYourOwn();
}
