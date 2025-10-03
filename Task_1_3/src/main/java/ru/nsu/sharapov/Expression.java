package ru.nsu.sharapov;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Abstract class of expression.
 */
public abstract class Expression {

    /**
     * Static method to parse string of variables and their values into Map: Variable -> Value.
     *
     * @param values String of variables and their values, for example: "x = 10; y = 13"
     * @return Map: String variable -> Integer value
     */
    protected static Map<String, Integer> parseVariables(String values) {
        Map<String, Integer> map = new HashMap<>();
        for (String part : values.split(";")) {
            String[] keyValue = part.split("=");
            String key = keyValue[0].trim();
            Integer value = Integer.parseInt(keyValue[1].trim());
            map.put(key, value);
        }
        return map;
    }

    /**
     * Abstract print.
     *
     * @param writer Writer to the file or console
     */
    public void print(PrintWriter writer) {
        writer.print(this);
    }

    /**
     * String representation of expressions.
     *
     * @return string representation
     */
    public abstract String toString();

    /**
     * Abstract derivative.
     *
     * @param variable String variable
     * @return New expression of the resulting derivative
     */
    public abstract Expression derivative(String variable);

    /**
     * Abstract evaluation with mapped variables and their values.
     *
     * @param varsValues Map of variables and their values
     * @return Result of evaluation
     * @throws NoSuchElementException if required variable is not found in varsValues
     */
    protected abstract double evalMapped(Map<String, Integer> varsValues)
        throws NoSuchElementException;

    /**
     * Abstract evaluation.
     *
     * @param varsValues String of variables and their values, for example: "x = 10; y = 13"
     * @return result of evaluation
     * @throws NoSuchElementException if required variable is not found in varsValues
     */
    public double eval(String varsValues) throws NoSuchElementException {
        return evalMapped(parseVariables(varsValues));
    }

    /**
     * Abstract equality of expressions.
     *
     * @param obj the reference object with which to compare.
     * @return equal or not
     */
    public abstract boolean equals(Object obj);
}
