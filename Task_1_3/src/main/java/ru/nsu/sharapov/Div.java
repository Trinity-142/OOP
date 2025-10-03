package ru.nsu.sharapov;

import java.util.Map;

/**
 * Subclass of division operation.
 */
public class Div extends BinaryOperation {

    /**
     * Constructor.
     *
     * @param left  Left expression
     * @param right Right expression
     */
    public Div(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public char getSign() {
        return '/';
    }

    /**
     * Take derivative by variable.
     *
     * @param variable String variable
     * @return New expression of the resulting derivative
     */
    @Override
    public Expression derivative(String variable) {
        return new Div(new Sub(new Mul(left.derivative(variable), right),
            new Mul(left, right.derivative(variable))), new Mul(left, left));
    }

    @Override
    public double evalMapped(Map<String, Integer> varsValues) {
        return left.evalMapped(varsValues) / right.evalMapped(varsValues);
    }
}
