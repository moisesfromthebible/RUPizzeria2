package com.example.rupizzeria2.model;

/**
 * Enum class for sizes
 *
 * @author Moises Cespedes Moreno, Binoy Patel
 */
public enum Size {
    /** Small pizza size */
    SMALL,
    /** Medium pizza size */
    MEDIUM,
    /** Large pizza size */
    LARGE;

    /**
     * ToString for size
     *
     * @return String representation
     */
    @Override
    public String toString(){
        return name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();
    }
}
