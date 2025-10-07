package ru.nsu.sharapov;

import java.util.Map;
import java.util.Objects;

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

    /**
     * Get Add sign.
     *
     * @return '+'
     */
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

    /**
     * Addition evaluation.
     *
     * @param varsValues Map of variables and their values
     * @return Result of evaluation
     */
    @Override
    public double evalMapped(Map<String, Integer> varsValues) {
        return left.evalMapped(varsValues) + right.evalMapped(varsValues);
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
            return new Number(a.getValue() + b.getValue());
        }
        if (Objects.equals(simplifiedLeft, zero)) {
            return simplifiedRight;
        }
        if (Objects.equals(simplifiedRight, zero)) {
            return simplifiedLeft;
        }
        return new Add(simplifiedLeft, simplifiedRight);
    }


}
