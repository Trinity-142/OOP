package ru.nsu.sharapov;

/**
 * Subclass of division operation.
 */
public class Div extends BinaryOperation {

    /**
     * Constructor.
     *
     * @param left Left expression
     * @param right Right expression
     */
    public Div(Expression left, Expression right) {
        super(left, right, Operation.DIV);
    }
}
