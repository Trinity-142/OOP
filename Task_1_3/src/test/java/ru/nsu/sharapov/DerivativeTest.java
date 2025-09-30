package ru.nsu.sharapov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.nsu.sharapov.Main.buildExpr;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

public class DerivativeTest {

    String path = "actual.txt";

    @Test
    void derivativeVar() {
        try (PrintWriter writer = new PrintWriter(path)) {
            String str_expr = "(3+(2*x))";
            Expression expr = buildExpr(str_expr);
            Expression de = expr.derivative("x");
            de.print(writer);
            writer.flush();
            String actual = Files.readString(Path.of(path));
            String expected = "(0+((0*x)+(2*1)))";
            assertEquals(expected, actual);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + path, e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void derivativeVars() {
        try (PrintWriter writer = new PrintWriter(path)) {
            String str_expr = "(3-(x/y))";
            Expression expr = buildExpr(str_expr);
            Expression de = expr.derivative("x");
            de.print(writer);
            writer.flush();
            String actual = Files.readString(Path.of(path));
            String expected = "(0-(((1*y)-(x*0))/(x*x)))";
            assertEquals(expected, actual);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + path, e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
