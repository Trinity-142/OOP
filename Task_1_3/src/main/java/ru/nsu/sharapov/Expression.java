package ru.nsu.sharapov;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

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
    public static Map<String, Integer> parseVariables(String values) {
        Map<String, Integer> map = new HashMap<>();
        for (String part : values.split(";")) {
            String[] key_value = part.split("=");
            String key = key_value[0].trim();
            Integer value = Integer.parseInt(key_value[1].trim());
            map.put(key, value);
        }
        return map;
    }

    /**
     * Abstract print.
     *
     * @param writer Writer to the file or console
     */
    public abstract void print(PrintWriter writer);

    /**
     * Abstract derivative.
     *
     * @param variable String variable
     * @return New expression of the resulting derivative
     */
    public abstract Expression derivative(String variable);

    /**
     * Abstract evaluation.
     *
     * @param varsValues Map of variables and their values
     * @return Result of evaluation
     */
    public abstract double eval(Map<String, Integer> varsValues);


}
