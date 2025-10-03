package ru.nsu.sharapov;

import java.util.Map;

/**
 * Superclass of binary operations: add, sub, mul, div.
 */
public abstract class BinaryOperation extends Expression {

    protected final Expression left;
    protected Expression right;

    /**
     * Constructor of binary expression.
     *
     * @param left  Left expression
     * @param right Right expression
     */
    public BinaryOperation(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Right expression setter.
     *
     * @param right Right expression
     */
    public void setRight(Expression right) {
        this.right = right;
    }

    /**
     * Binary expression print method.
     *
     * @return Stpresentation
     */
    @Override
    public String toString() {
        return "(" + left + getSign() + right + ")";
    }

    public abstract char getSign();

    /**
     * Take derivative by variable.
     *
     * @param variable String variable
     * @return New expression of the resulting derivative
     */
    @Override
    public abstract Expression derivative(String variable);

    /**
     * Expression evaluation.
     *
     * @param varsValues Map of variables and their values
     * @return Result of evaluation
     */
    @Override
    public abstract double evalMapped(Map<String, Integer> varsValues);
}
