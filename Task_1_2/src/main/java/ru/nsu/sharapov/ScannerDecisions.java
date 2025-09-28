package ru.nsu.sharapov;

import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Реализация для CLI режима со сканером ввода игрока.
 */
public class ScannerDecisions implements Decisions {

    private final Scanner scanner;

    /**
     * Конструктор принимающий сканер.
     *
     * @param scanner Сканер
     */
    public ScannerDecisions(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public int askTakeCard() {
        int decision;
        while (true) {
            try {
                System.out.println(
                    "Введите “1”, чтобы взять карту, и “0”, чтобы остановиться...\n");
                decision = scanner.nextInt();
                break;
            } catch (InputMismatchException exc) {
                System.out.println("Некорректный ввод");
                scanner.next();
            }
        }
        return decision;
    }
}
