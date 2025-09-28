package ru.nsu.sharapov;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;
import org.junit.jupiter.api.Test;

public class ScannerDecisionsTest {

    @Test
    void askTakeCard() {
        Scanner decisions = new Scanner("1 0");
        ScannerDecisions scannerDecisions = new ScannerDecisions(decisions);
        assertEquals(1, scannerDecisions.askTakeCard());
    }

    @Test
    void exceptionHandling() {
        Scanner decisions = new Scanner("qwe 0");
        ScannerDecisions scannerDecisions = new ScannerDecisions(decisions);
        assertDoesNotThrow(scannerDecisions::askTakeCard);
    }
}
