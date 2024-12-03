package com.example.rupizzeria2.model;

/**
 * Enum class for toppings
 *
 * @author Moises Cespedes Moreno, Binoy Patel
 */
public enum Topping {
    /** Sausage enum */
    SAUSAGE,
    /** Pepperoni enum */
    PEPPERONI,
    /** Green pepper enum */
    GREEN_PEPPER,
    /** Onion enum */
    ONION,
    /** Mushroom enum */
    MUSHROOM,
    /** BBQ chicken enum */
    BBQ_CHICKEN,
    /** Provolone enum */
    PROVOLONE,
    /** Cheddar enum */
    CHEDDAR,
    /** Beef enum */
    BEEF,
    /** Ham enum */
    HAM,
    /** Pineapple enum */
    PINEAPPLE,
    /** Olives enum */
    BLACK_OLIVES,
    /** Spinach enum */
    SPINACH;

    /**
     * This returns a topping object based on a string
     *
     * @param string String argument
     * @return Topping object
     */
    public static Topping getTopping(String string) {
        String normalizedString = string.toLowerCase();

        return switch (normalizedString) {
            case "sausage" -> SAUSAGE;
            case "pepperoni" -> PEPPERONI;
            case "green pepper" -> GREEN_PEPPER;
            case "onion" -> ONION;
            case "mushroom" -> MUSHROOM;
            case "bbq chicken" -> BBQ_CHICKEN;
            case "provolone" -> PROVOLONE;
            case "cheddar" -> CHEDDAR;
            case "beef" -> BEEF;
            case "ham" -> HAM;
            case "spinach" -> SPINACH;
            case "pineapple" -> PINEAPPLE;
            case "black olives" -> BLACK_OLIVES;
            default -> throw new IllegalArgumentException("Unknown topping: " + string);
        };
    }

    /**
     * toString method for topping class
     *
     * @return String representation of class
     */
    @Override
    public String toString() {
        return name().replace('_', ' ').toLowerCase();
    }
}
