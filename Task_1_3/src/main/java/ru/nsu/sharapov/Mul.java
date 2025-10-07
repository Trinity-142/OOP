package ru.nsu.sharapov;

import java.util.Map;
import java.util.Objects;

/**
 * Subclass of multiplication operation.
 */
public class Mul extends BinaryOperation {

    /**
     * Constructor.
     *
     * @param left  Left expression
     * @param right Right expression
     */
    public Mul(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * Get Mul sign.
     *
     * @return '*'
     */
    @Override
    public char getSign() {
        return '*';
    }

    @Override
    public Expression derivative(String variable) {
        return new Add(new Mul(left.derivative(variable), right),
            new Mul(left, right.derivative(variable)));
    }

    /**
     * Multiplication evaluation.
     *
     * @param varsValues Map of variables and their values
     * @return Result of evaluation
     */
    @Override
    public double evalMapped(Map<String, Integer> varsValues) {
        return left.evalMapped(varsValues) * right.evalMapped(varsValues);
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
        if (left instanceof Number a && right instanceof Number b) {
            return new Number(a.getValue() * b.getValue());
        }
        if (Objects.equals(simplifiedLeft, zero) || Objects.equals(simplifiedRight, zero)) {
            return zero;
        }
        if (Objects.equals(simplifiedLeft, one)) {
            return simplifiedRight;
        }
        if (Objects.equals(simplifiedRight, one)) {
            return simplifiedLeft;
        }
        return new Mul(simplifiedLeft, simplifiedRight);
    }
}
