package org.example;

import java.util.List;

public class PerformanceTester {
    public static void testListPerformance(List<Integer> list, int testSize, String listType) {
        // Тестируем метод add
        long startTime = System.nanoTime();
        for (int i = 0; i < testSize; i++) {
            list.add(i);
        }
        long endTime = System.nanoTime();
        System.out.printf("%-15s %-15d %-15.2f %-15s%n", "add", testSize, (endTime - startTime) / 1_000_000.0, listType);

        // Тестируем метод get
        startTime = System.nanoTime();
        for (int i = 0; i < testSize; i++) {
            list.get(i);
        }
        endTime = System.nanoTime();
        System.out.printf("%-15s %-15d %-15.2f %-15s%n", "get", testSize, (endTime - startTime) / 1_000_000.0, listType);

        // Тестируем метод remove
        startTime = System.nanoTime();
        for (int i = testSize - 1; i >= 0; i--) {
            list.remove(i);
        }
        endTime = System.nanoTime();
        System.out.printf("%-15s %-15d %-15.2f %-15s%n", "remove", testSize, (endTime - startTime) / 1_000_000.0, listType);
    }
}
