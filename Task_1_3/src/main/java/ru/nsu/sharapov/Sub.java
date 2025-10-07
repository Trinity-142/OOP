package ru.nsu.sharapov;

import java.util.Map;
import java.util.Objects;

/**
 * Subclass of subtraction operation.
 */
public class Sub extends BinaryOperation {

    public Sub(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * Get Sub sign.
     *
     * @return '-'
     */
    @Override
    public char getSign() {
        return '-';
    }

    @Override
    public Expression derivative(String variable) {
        return new Sub(left.derivative(variable), right.derivative(variable));
    }

    /**
     * Subtraction evaluation.
     *
     * @param varsValues Map of variables and their values
     * @return Result of evaluation
     */
    @Override
    public double evalMapped(Map<String, Integer> varsValues) {
        return left.evalMapped(varsValues) - right.evalMapped(varsValues);
    }

    /**
     * Simplifies expression.
     *
     * @return simplified expression
     */
    @Override
    public Expression simplify() {
        Expression simplifiedLeft = left.simplify();
        Expression simplifiedRight = right.simplify();
        Number zero = new Number(0);
        if (simplifiedLeft instanceof Number a && simplifiedRight instanceof Number b) {
            return new Number(a.getValue() - b.getValue());
        }
        if (simplifiedLeft == simplifiedRight) {
            return zero;
        }
        if (Objects.equals(simplifiedRight, zero)) {
            return simplifiedLeft;
        }
        return new Sub(simplifiedLeft, simplifiedRight);
    }
}
