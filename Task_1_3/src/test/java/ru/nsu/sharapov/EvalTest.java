package ru.nsu.sharapov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.nsu.sharapov.Parser.buildExpr;

import org.junit.jupiter.api.Test;

public class EvalTest {

    @Test
    void add() {
        String strExpr = "(x+y)";
        Expression expr = buildExpr(strExpr);
        double actual = expr.eval("x = 10; y = 13");
        double expected = 23;
        assertEquals(expected, actual);
    }

    @Test
    void sub() {
        String strExpr = "(x-y)";
        Expression expr = buildExpr(strExpr);
        double actual = expr.eval("x = 10; y = 13");
        double expected = -3;
        assertEquals(expected, actual);
    }

    @Test
    void mul() {
        String strExpr = "(x*y)";
        Expression expr = buildExpr(strExpr);
        double actual = expr.eval("x = 10; y = 13");
        double expected = 130;
        assertEquals(expected, actual);
    }

    @Test
    void div() {
        String strExpr = "(x/y)";
        Expression expr = buildExpr(strExpr);
        double actual = expr.eval("x = 13; y = 10");
        double expected = 1.3;
        assertEquals(expected, actual);
    }

    @Test
    void complex() {
        String strExpr = "(3+(((2*x)/y)-z))";
        Expression expr = buildExpr(strExpr);
        double actual = expr.eval("x = 15; y = 10; z = 5");
        double expected = 1;
        assertEquals(expected, actual);
    }
}
