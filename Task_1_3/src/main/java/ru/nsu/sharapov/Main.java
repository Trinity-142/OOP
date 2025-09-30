package ru.nsu.sharapov;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class Main {

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
                if (!stack.isEmpty() && stack.get(stack.size() - 1) instanceof Number) {
                    stack.remove(stack.size() - 1);
                }
                number.append(symbol);
                stack.add(new Number(Integer.parseInt(String.valueOf(number))));
            } else if (Character.isLetter(symbol)) {
                number = new StringBuilder();
                if (!stack.isEmpty() && stack.get(stack.size() - 1) instanceof Variable) {
                    stack.remove(stack.size() - 1);
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
                        Expression right = stack.remove(stack.size() - 1);
                        try {
                            BinaryOperation exprWithoutRight = (BinaryOperation) stack.remove(
                                stack.size() - 1);
                            stack.remove(stack.size() - 1); // null "("
                            exprWithoutRight.setRight(right);
                            stack.add(exprWithoutRight);
                        } catch (ClassCastException exception) {
                            throw new ClassCastException("Expected BinaryOperation");
                        }
                        break;
                    case (Operation.ADD_CHAR):
                        stack.add(new Add(stack.remove(stack.size() - 1), null));
                        break;
                    case (Operation.SUB_CHAR):
                        stack.add(new Sub(stack.remove(stack.size() - 1), null));
                        break;
                    case (Operation.MUL_CHAR):
                        stack.add(new Mul(stack.remove(stack.size() - 1), null));
                        break;
                    case (Operation.DIV_CHAR):
                        stack.add(new Div(stack.remove(stack.size() - 1), null));
                        break;
                    default:
                        throw new IllegalArgumentException("Unexpected character: " + symbol);

                }
            }
        }
        return stack.remove(0);
    }

    public static void main(String[] args) {
        PrintWriter consoleWriter = new PrintWriter(System.out);
        for (String strExpr : args) {
            Expression expr = buildExpr(strExpr);
            expr.print(consoleWriter);
        }
    }
}