package ru.nsu.sharapov;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    /**
     * Expression object builder by string.
     *
     * @param string String expression
     * @return New expression
     */
    public static Expression buildExpr(String string) {
        StringBuilder number = new StringBuilder();
        StringBuilder variable = new StringBuilder();
        List<Expression> stack = new ArrayList<>();
        for (char symbol : string.toCharArray()) {

            if (Character.isDigit(symbol)) {
                variable = new StringBuilder();
                if (!stack.isEmpty() && stack.getLast() instanceof Number) {
                    stack.removeLast();
                }
                number.append(symbol);
                stack.add(new Number(Integer.parseInt(String.valueOf(number))));
            } else if (Character.isLetter(symbol)) {
                number = new StringBuilder();
                if (!stack.isEmpty() && stack.getLast() instanceof Variable) {
                    stack.removeLast();
                }
                variable.append(symbol);
                stack.add(new Variable(String.valueOf(variable)));
            } else {
                number = new StringBuilder();
                variable = new StringBuilder();
                switch (symbol) {
                    case '(':
                        stack.add(null);
                        break;
                    case ')':
                        Expression right = stack.removeLast();
                        try {
                            BinaryOperation exprWithoutRight = (BinaryOperation) stack.removeLast();
                            stack.removeLast(); // null "("
                            exprWithoutRight.setRight(right);
                            stack.add(exprWithoutRight);
                        } catch (ClassCastException exception) {
                            throw new ClassCastException("Expected BinaryOperation");
                        }
                        break;
                    case ('+'):
                        stack.add(new Add(stack.removeLast(), null));
                        break;
                    case ('-'):
                        stack.add(new Sub(stack.removeLast(), null));
                        break;
                    case ('*'):
                        stack.add(new Mul(stack.removeLast(), null));
                        break;
                    case ('/'):
                        stack.add(new Div(stack.removeLast(), null));
                        break;
                    default:
                        throw new IllegalArgumentException("Unexpected character: " + symbol);

                }
            }
        }
        return stack.removeFirst();
    }
}
