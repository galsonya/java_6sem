package org.example;

import java.util.List;

public class PerformanceTester {
    public static void testListPerformance(List<Integer> list, int testSize) {
        // Тестируем метод add
        long startTime = System.nanoTime();
        for (int i = 0; i < testSize; i++) {
            list.add(i);
        }
        long endTime = System.nanoTime();
        System.out.printf("add: %d раз, время: %.2f мс%n", testSize, (endTime - startTime) / 1_000_000.0);

        // Тестируем метод get
        startTime = System.nanoTime();
        for (int i = 0; i < testSize; i++) {
            list.get(i);
        }
        endTime = System.nanoTime();
        System.out.printf("get: %d раз, время: %.2f мс%n", testSize, (endTime - startTime) / 1_000_000.0);

        // Тестируем метод remove
        startTime = System.nanoTime();
        for (int i = testSize - 1; i >= 0; i--) {
            list.remove(i);
        }
        endTime = System.nanoTime();
        System.out.printf("remove: %d раз, время: %.2f мс%n", testSize, (endTime - startTime) / 1_000_000.0);
    }
}
