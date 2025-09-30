package ru.nsu.sharapov;

/**
 * Subclass of addition operation.
 */
public class Add extends BinaryOperation {

    /**
     * Constructor.
     *
     * @param left Left expression
     * @param right Right expression
     */
    public Add(Expression left, Expression right) {
        super(left, right, Operation.ADD);
    }
}
