package ru.nsu.sharapov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.nsu.sharapov.Parser.buildExpr;

import org.junit.jupiter.api.Test;

public class DerivativeTest {

    @Test
    void add() {
        String strExpr = "(8+(a+b))";
        Expression expr = buildExpr(strExpr);
        Expression actual = expr.derivative("a");
        Add expected = new Add(new Number(0), new Add(new Number(1), new Number(0)));
        assertEquals(expected, actual);
    }

    @Test
    void sub() {
        String strExpr = "((a-8)-b)";
        Expression expr = buildExpr(strExpr);
        Expression actual = expr.derivative("b");
        Sub expected = new Sub(new Sub(new Number(0), new Number(0)), new Number(1));
        assertEquals(expected, actual);
    }

    @Test
    void mul() {
        String strExpr = "((8*b)*a)";
        Expression expr = buildExpr(strExpr);
        Expression actual = expr.derivative("a");
        Add expected = new Add(new Mul(new Add(new Mul(new Number(0), new Variable("b")),
            new Mul(new Number(8), new Number(0))), new Variable("a")),
            new Mul(new Mul(new Number(8), new Variable("b")), new Number(1)));
        assertEquals(expected, actual);
    }

    @Test
    void div() {
        String strExpr = "((a/b)/8)";
        Expression expr = buildExpr(strExpr);
        Expression actual = expr.derivative("b");
        Div expected = new Div(new Sub(new Mul(new Div(
            new Sub(new Mul(new Number(0), new Variable("b")),
                new Mul(new Variable("a"), new Number(1))),
            new Mul(new Variable("b"), new Variable("b"))), new Number(8)),
            new Mul(new Div(new Variable("a"), new Variable("b")), new Number(0))),
            new Mul(new Number(8), new Number(8)));
        assertEquals(expected, actual);
    }
    /*
    @Test
    void complex() {
        String strExpr = "(((a*8)-(b+c))/(d*e))";
        Expression expr = buildExpr(strExpr);
        Expression actual = expr.derivative("e");
        expected =
        assertEquals(expected, actual);
    }
    */
}
