package ru.nsu.sharapov;

/**
 * Subclass of multiplication operation.
 */
public class Mul extends BinaryOperation {

    /**
     * Constructor.
     *
     * @param left Left expression
     * @param right Right expression
     */
    public Mul(Expression left, Expression right) {
        super(left, right, Operation.MUL);
    }
}
