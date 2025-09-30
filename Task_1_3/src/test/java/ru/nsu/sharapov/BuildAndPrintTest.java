package ru.nsu.sharapov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.nsu.sharapov.Main.buildExpr;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

public class BuildAndPrintTest {

    String path = "actual.txt";

    @Test
    void buildAndPrint() {
        try (PrintWriter writer = new PrintWriter(path)) {
            String strExpr = "(33+(2*zxc))";
            Expression expr = buildExpr(strExpr);
            expr.print(writer);
            writer.flush();
            String actual = Files.readString(Path.of(path));
            assertEquals(strExpr, actual);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + path, e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
