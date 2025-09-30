package ru.nsu.sharapov;

import java.io.PrintWriter;
import java.util.Map;

/**
 * Superclass of binary operations: add, sub, mul, div.
 */
public class BinaryOperation extends Expression {

    private final Expression left;
    private final Operation operation;
    private Expression right;

    /**
     * Constructor of binary expression.
     *
     * @param left Left expression
     * @param right Right expression
     * @param operation Sign of expression
     */
    public BinaryOperation(Expression left, Expression right, Operation operation) {
        this.left = left;
        this.right = right;
        this.operation = operation;
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
     * @param writer Writer to the file or console
     */
    @Override
    public void print(PrintWriter writer) {
        writer.print("(");
        left.print(writer);
        writer.print(operation);
        right.print(writer);
        writer.print(")");
    }

    /**
     * Take derivative by variable.
     *
     * @param variable String variable
     * @return New expression of the resulting derivative
     */
    @Override
    public Expression derivative(String variable) {
        return switch (operation.getSign()) {
            case '+' -> new Add(left.derivative(variable), right.derivative(variable));
            case '-' -> new Sub(left.derivative(variable), right.derivative(variable));
            case '*' -> new Add(new Mul(left.derivative(variable), right),
                new Mul(left, right.derivative(variable)));
            default -> new Div(new Sub(new Mul(left.derivative(variable), right),
                new Mul(left, right.derivative(variable))), new Mul(left, left));
        };
    }

    /**
     * Expression evaluation.
     *
     * @param varsValues Map of variables and their values
     * @return Result of evaluation
     */
    @Override
    public double eval(Map<String, Integer> varsValues) {
        return switch (operation.getSign()) {
            case '+' -> left.eval(varsValues) + right.eval(varsValues);
            case '-' -> left.eval(varsValues) - right.eval(varsValues);
            case '*' -> left.eval(varsValues) * right.eval(varsValues);
            default -> left.eval(varsValues) / right.eval(varsValues);
        };
    }
}
