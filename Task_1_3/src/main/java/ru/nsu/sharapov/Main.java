package ru.nsu.sharapov;

import java.io.PrintWriter;


public class Main {


    public static void main(String[] args) {
        PrintWriter consoleWriter = new PrintWriter(System.out);
        for (String strExpr : args) {
            Expression expr = Parser.buildExpr(strExpr);
            expr.print(consoleWriter);
        }
    }
}