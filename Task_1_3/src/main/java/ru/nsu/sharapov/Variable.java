package ru.nsu.sharapov;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

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
     * String representation of variable.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return name;
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
     * @throws NoSuchElementException if required variable is not found in varsValues
     */
    @Override
    public double evalMapped(Map<String, Integer> varsValues) throws NoSuchElementException {
        if (!varsValues.containsKey(name)) {
            throw new NoSuchElementException("Variable \"" + name + "\" is undefined.");
        }
        return varsValues.get(name);
    }

    /**
     * Equality of variables.
     *
     * @param obj the reference object with which to compare
     * @return variable names equal or not
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Variable other) {
            return Objects.equals(this.name, other.name);
        } else {
            return false;
        }
    }
}
