package ru.nsu.sharapov;

import java.util.Map;
import java.util.Objects;

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

    /**
     * Get Div sign.
     *
     * @return '/'
     */
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
            new Mul(left, right.derivative(variable))), new Mul(right, right));
    }

    /**
     * Division evaluation.
     *
     * @param varsValues Map of variables and their values
     * @return Result of evaluation
     */
    @Override
    public double evalMapped(Map<String, Integer> varsValues) {
        return left.evalMapped(varsValues) / right.evalMapped(varsValues);
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
        Number one = new Number(1);
        if (Objects.equals(simplifiedLeft, zero)) {
            return zero;
        }
        if (Objects.equals(simplifiedRight, one)) {
            return simplifiedLeft;
        }
        return new Div(simplifiedLeft, simplifiedRight);
    }
}
