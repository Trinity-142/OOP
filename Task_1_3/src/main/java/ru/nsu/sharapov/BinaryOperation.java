package ru.nsu.sharapov;

import java.util.Map;

/**
 * Superclass of binary operations: add, sub, mul, div.
 */
public abstract class BinaryOperation extends Expression {

    protected Expression left;
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
     * String representation of binary expression.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return "(" + left + getSign() + right + ")";
    }

    /**
     * Get operation sign.
     *
     * @return operation sign symbol
     */
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

    /**
     * Equality of binary expressions.
     *
     * @param obj the reference object with which to compare.
     * @return equal or not
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof BinaryOperation other) {
            return this.left.equals(other.left) && this.right.equals(other.right)
                && this.getSign() == other.getSign();
        } else {
            return false;
        }
    }
}
