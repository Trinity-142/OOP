package ru.nsu.sharapov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.nsu.sharapov.Parser.buildExpr;

import org.junit.jupiter.api.Test;

public class DerivativeTest {

    @Test
    void add() {
        String strExpr = "(8+(a+b))";
        Expression expr = buildExpr(strExpr);
        Expression actual = expr.derivative("a").simplify();
        Number expected = new Number(1);
        assertEquals(expected, actual);
    }

    @Test
    void sub() {
        String strExpr = "((a-8)-b)";
        Expression expr = buildExpr(strExpr);
        Expression actual = expr.derivative("b").simplify();
        Number expected = new Number(-1);
        assertEquals(expected, actual);
    }

    @Test
    void mul() {
        String strExpr = "((8*b)*a)";
        Expression expr = buildExpr(strExpr);
        Expression actual = expr.derivative("a").simplify();
        Mul expected = new Mul(new Number(8), new Variable("b"));
        assertEquals(expected, actual);
    }

    @Test
    void div() {
        String strExpr = "((a/b)/8)";
        Expression expr = buildExpr(strExpr);
        Expression actual = expr.derivative("b").simplify();
        Div expected = new Div(new Mul(new Div(new Sub(new Number(0), new Variable("a")),
            new Mul(new Variable("b"), new Variable("b"))), new Number(8)), new Number(64));
        assertEquals(expected, actual);
    }

    @Test
    void complex() {
        String strExpr = "(((a*8)-(b+c))/(d*e))";
        Expression expr = buildExpr(strExpr);
        Expression actual = expr.derivative("e").simplify();
        System.out.println(actual);
        Div expected =
            new Div(
                new Sub(
                    new Number(0),
                    new Mul(
                        new Sub(
                            new Mul(
                                new Variable("a"),
                                new Number(8)),
                            new Add(
                                new Variable("b"),
                                new Variable("c"))),
                        new Variable("d"))),
                new Mul(
                    new Mul(
                        new Variable("d"),
                        new Variable("e")),
                    new Mul(
                        new Variable("d"),
                        new Variable("e"))));
        assertEquals(expected, actual);
    }
}
