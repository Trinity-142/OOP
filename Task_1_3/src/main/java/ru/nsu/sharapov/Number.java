package ru.nsu.sharapov;

import java.util.Map;

/**
 * Subclass of constant number.
 */
public class Number extends Expression {

    private final int value;

    /**
     * Constructor.
     *
     * @param value Number value
     */
    public Number(int value) {
        this.value = value;
    }

    /**
     * String representation of number.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return String.valueOf(value);
    }

    /**
     * Derivative of a number.
     *
     * @param variable String variable
     * @return Number object with zero value
     */
    @Override
    public Expression derivative(String variable) {
        return new Number(0);
    }

    /**
     * Evaluation of a number.
     *
     * @param varsValues Map of variables and their values
     * @return Number value
     */
    @Override
    public double evalMapped(Map<String, Integer> varsValues) {
        return value;
    }

    /**
     * Equality of numbers.
     *
     * @param obj the reference object with which to compare.
     * @return equal or not
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Number other) {
            return this.value == other.value;
        } else {
            return false;
        }
    }
}
