package ru.nsu.sharapov;

import java.io.PrintWriter;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Subclass of variable.
 */
public class Variable extends Expression {

    private final String name;

    /**
     * Constructor.
     *
     * @param name Variable name
     */
    public Variable(String name) {
        this.name = name;
    }

    /**
     * Variable name print method.
     *
     * @param writer Writer to the file or console
     */
    @Override
    public void print(PrintWriter writer) {
        writer.print(name);
    }

    /**
     * Derivative of a variable.
     *
     * @param variable String variable
     * @return Number object
     */
    @Override
    public Expression derivative(String variable) {
        if (this.name.equals(variable)) {
            return new Number(1);
        } else {
            return new Number(0);
        }
    }

    /**
     * Evaluation of a variable
     *
     * @param varsValues Map of variables and their values
     * @return Variable value
     */
    @Override
    public double eval(Map<String, Integer> varsValues) {
        if (!varsValues.containsKey(name)) {
            throw new NoSuchElementException("Variable \"" + name + "\" is undefined.");
        }
        return varsValues.get(name);
    }
}
