package ru.nsu.sharapov;

import java.util.Map;

/**
 * Subclass of subtraction operation.
 */
public class Sub extends BinaryOperation {

    public Sub(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public char getSign() {
        return '-';
    }

    @Override
    public Expression derivative(String variable) {
        return new Sub(left.derivative(variable), right.derivative(variable));
    }

    @Override
    public double evalMapped(Map<String, Integer> varsValues) {
        return left.evalMapped(varsValues) - right.evalMapped(varsValues);
    }
}
