package ru.nsu.sharapov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.nsu.sharapov.Parser.buildExpr;

import org.junit.jupiter.api.Test;

public class BuildTest {

    @Test
    void add() {
        String strExpr = "((a+b)+128)";
        Expression actual = buildExpr(strExpr);
        Expression expected = new Add(new Add(new Variable("a"), new Variable("b")),
            new Number(128));
        assertEquals(expected, actual);
    }

    @Test
    void sub() {
        String strExpr = "(128-(a-b))";
        Expression actual = buildExpr(strExpr);
        Expression expected = new Sub(new Number(128),
            new Sub(new Variable("a"), new Variable("b")));
        assertEquals(expected, actual);
    }

    @Test
    void mul() {
        String strExpr = "(a*(b*128))";
        Expression actual = buildExpr(strExpr);
        Expression expected = new Mul(new Variable("a"),
            new Mul(new Variable("b"), new Number(128)));
        assertEquals(expected, actual);
    }

    @Test
    void div() {
        String strExpr = "(b/(a/128))";
        Expression actual = buildExpr(strExpr);
        Expression expected = new Div(new Variable("b"),
            new Div(new Variable("a"), new Number(128)));
        assertEquals(expected, actual);
    }

    @Test
    void complex() {
        String strExpr = "(((a+b)/(128*c))-8)";
        Expression actual = buildExpr(strExpr);
        Expression expected = new Sub(new Div(new Add(new Variable("a"), new Variable("b")),
            new Mul(new Number(128), new Variable("c"))), new Number(8));
        assertEquals(expected, actual);
    }
}
