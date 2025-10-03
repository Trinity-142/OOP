package ru.nsu.sharapov;

import java.util.Stack;

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
        Stack<Expression> stack = new Stack<>();
        for (char symbol : string.toCharArray()) {

            if (Character.isDigit(symbol)) {
                variable = new StringBuilder();
                if (!stack.isEmpty() && stack.peek() instanceof Number) {
                    stack.pop();
                }
                number.append(symbol);
                stack.add(new Number(Integer.parseInt(String.valueOf(number))));
            } else if (Character.isLetter(symbol)) {
                number = new StringBuilder();
                if (!stack.isEmpty() && stack.peek() instanceof Variable) {
                    stack.pop();
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
                        Expression right = stack.pop();
                        try {
                            BinaryOperation exprWithoutRight = (BinaryOperation) stack.pop();
                            stack.pop(); // null "("
                            exprWithoutRight.setRight(right);
                            stack.add(exprWithoutRight);
                        } catch (ClassCastException exception) {
                            throw new ClassCastException("Expected BinaryOperation");
                        }
                        break;
                    case ('+'):
                        stack.add(new Add(stack.pop(), null));
                        break;
                    case ('-'):
                        stack.add(new Sub(stack.pop(), null));
                        break;
                    case ('*'):
                        stack.add(new Mul(stack.pop(), null));
                        break;
                    case ('/'):
                        stack.add(new Div(stack.pop(), null));
                        break;
                    default:
                        throw new IllegalArgumentException("Unexpected character: " + symbol);

                }
            }
        }
        return stack.pop();
    }
}
