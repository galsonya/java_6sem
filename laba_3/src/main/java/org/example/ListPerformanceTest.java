package org.example;

import java.util.ArrayList;
import java.util.LinkedList;

public class ListPerformanceTest {
    public static void main(String[] args) {
        final int TEST_SIZE = 10000; // Количество операций

        // Тестируем ArrayList
        System.out.println("Тестирование ArrayList:");
        PerformanceTester.testListPerformance(new ArrayList<>(), TEST_SIZE);

        // Тестируем LinkedList
        System.out.println("\nТестирование LinkedList:");
        PerformanceTester.testListPerformance(new LinkedList<>(), TEST_SIZE);
    }
}
