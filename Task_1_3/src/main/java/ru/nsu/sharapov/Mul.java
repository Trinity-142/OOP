package ru.nsu.sharapov;

import java.util.Map;

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
}
