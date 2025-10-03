package ru.nsu.sharapov;

import java.util.Map;

/**
 * Subclass of addition operation.
 */
public class Add extends BinaryOperation {

    /**
     * Constructor.
     *
     * @param left  Left expression
     * @param right Right expression
     */
    public Add(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public char getSign() {
        return '+';
    }


    /**
     * Take derivative by variable.
     *
     * @param variable String variable
     * @return New expression of the resulting derivative
     */
    @Override
    public Expression derivative(String variable) {
        return new Add(left.derivative(variable), right.derivative(variable));
    }

    @Override
    public double evalMapped(Map<String, Integer> varsValues) {
        return left.evalMapped(varsValues) + right.evalMapped(varsValues);
    }
}
