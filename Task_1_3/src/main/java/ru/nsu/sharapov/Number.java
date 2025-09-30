package ru.nsu.sharapov;

import java.io.PrintWriter;
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
     * @param writer Writer to the file or console
     */
    @Override
    public void print(PrintWriter writer) {
        writer.print(value);
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
    public double eval(Map<String, Integer> varsValues) {
        return value;
    }
}
