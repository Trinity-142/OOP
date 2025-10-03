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
     * Number print method.
     *
     * @return .
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
     * Evaluation of a number = number.
     *
     * @param varsValues Map of variables and their values
     * @return Number value
     */
    @Override
    public double evalMapped(Map<String, Integer> varsValues) {
        return value;
    }
}
