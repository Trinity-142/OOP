package ru.nsu.sharapov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;

public class SimplifyTest {

    @Test
    void addZeroRight() {
        Expression expr = new Add(new Variable("x"), new Number(0));
        Expression actual = expr.simplify();
        Expression expected = new Variable("x");
        assertEquals(expected, actual);
    }

    @Test
    void addZeroLeft() {
        Expression expr = new Add(new Number(0), new Variable("x"));
        Expression actual = expr.simplify();
        Expression expected = new Variable("x");
        assertEquals(expected, actual);
    }

    @Test
    void divZero() {
        Expression expr = new Div(new Number(0), new Variable("x"));
        Expression actual = expr.simplify();
        Expression expected = new Number(0);
        assertEquals(expected, actual);
    }

    @Test
    void divOne() {
        Expression expr = new Div(new Variable("x"), new Number(1));
        Expression actual = expr.simplify();
        Expression expected = new Variable("x");
        assertEquals(expected, actual);
    }

    @Test
    void mulZero() {
        Expression expr = new Mul(new Variable("x"), new Number(0));
        Expression actual = expr.simplify();
        Expression expected = new Number(0);
        assertEquals(expected, actual);
    }

    @Test
    void mulOneRight() {
        Expression expr = new Mul(new Variable("x"), new Number(1));
        Expression actual = expr.simplify();
        Expression expected = new Variable("x");
        assertEquals(expected, actual);
    }

    @Test
    void mulOneLeft() {
        Expression expr = new Mul(new Number(1), new Variable("x"));
        Expression actual = expr.simplify();
        Expression expected = new Variable("x");
        assertEquals(expected, actual);
    }

    @Test
    void subEquals() {
        Expression expr = new Sub(new Variable("x"), new Variable("x"));
        Expression actual = expr.simplify();
        Expression expected = new Number(0);
        assertEquals(expected, actual);
    }

    @Test
    void noVars() {
        Expression expr = new Add(new Number(5), new Number(10));
        Expression actual = expr.simplify();
        Expression expected = new Number(15);
        assertEquals(expected, actual);
    }
}
