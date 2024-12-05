package com.example.rupizzeria2.model;

import androidx.annotation.NonNull;

/**
 * Enum class for crusts
 *
 * @author Moises Cespedes Moreno, Binoy Patel
 */
public enum Crust {
    /** Deep dish enum */
    DEEP_DISH,
    /** Thin enum */
    THIN,
    /** Hand tossed enum */
    HAND_TOSSED,
    /** Pan enum */
    PAN,
    /** Stuffed enum */
    STUFFED,
    /** Brooklyn enum */
    BROOKLYN;

    /**
     * Returns a String representation of the crust
     *
     * @return String representation
     */
    @NonNull
    @Override
    public String toString(){
        return name().replace('_', ' ');
    }
}
