package ru.nsu.sharapov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.nsu.sharapov.Parser.buildExpr;

import org.junit.jupiter.api.Test;

public class PrintTest {

    @Test
    void add() {
        String expected = "((a+b)+128)";
        Expression expr = buildExpr(expected);
        String actual = expr.toString();
        assertEquals(expected, actual);
    }

    @Test
    void sub() {
        String expected = "(128-(a-b))";
        Expression expr = buildExpr(expected);
        String actual = expr.toString();
        assertEquals(expected, actual);
    }

    @Test
    void mul() {
        String expected = "(a*(b*128))";
        Expression expr = buildExpr(expected);
        String actual = expr.toString();
        assertEquals(expected, actual);
    }

    @Test
    void div() {
        String expected = "(b/(a/128))";
        Expression expr = buildExpr(expected);
        String actual = expr.toString();
        assertEquals(expected, actual);
    }

    @Test
    void complex() {
        String expected = "(((a+b)/(128*c))-8)";
        Expression expr = buildExpr(expected);
        String actual = expr.toString();
        assertEquals(expected, actual);
    }
}
