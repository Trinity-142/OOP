package ru.nsu.sharapov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.nsu.sharapov.Parser.buildExpr;

import org.junit.jupiter.api.Test;

public class DerivativeTest {

    @Test
    void add() {
        String strExpr = "(8+(a+b))";
        Expression expr = buildExpr(strExpr);
        Expression de = expr.derivative("a");
        String actual = de.toString();
        String expected = "(0+(1+0))";
        assertEquals(expected, actual);
    }

    @Test
    void sub() {
        String strExpr = "((a-8)-b)";
        Expression expr = buildExpr(strExpr);
        Expression de = expr.derivative("b");
        String actual = de.toString();
        String expected = "((0-0)-1)";
        assertEquals(expected, actual);
    }

    @Test
    void mul() {
        String strExpr = "((8*b)*a)";
        Expression expr = buildExpr(strExpr);
        Expression de = expr.derivative("a");
        String actual = de.toString();
        String expected = "((((0*b)+(8*0))*a)+((8*b)*1))";
        assertEquals(expected, actual);
    }

    @Test
    void div() {
        String strExpr = "((a/b)/8)";
        Expression expr = buildExpr(strExpr);
        Expression de = expr.derivative("b");
        String actual = de.toString();
        String expected = "((((((0*b)-(a*1))/(b*b))*8)-((a/b)*0))/(8*8))";
        assertEquals(expected, actual);
    }

    @Test
    void complex() {
        String strExpr = "(((a*8)-(b+c))/(d*e))";
        Expression expr = buildExpr(strExpr);
        Expression de = expr.derivative("e");
        String actual = de.toString();
        String expected = "((((((0*8)+(a*0))-(0+0))*(d*e))-(((a*8)-(b+c))*((0*e)+(d*1))))/((d*e)*(d*e)))";
        assertEquals(expected, actual);
    }
}
