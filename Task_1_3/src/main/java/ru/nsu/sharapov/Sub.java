package ru.nsu.sharapov;

/**
 * Subclass of subtraction operation.
 */
public class Sub extends BinaryOperation {

    public Sub(Expression left, Expression right) {
        super(left, right, Operation.SUB);
    }
}
