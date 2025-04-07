package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListPerformanceTest {
    public static void main(String[] args) {
        final int TEST_SIZE = 10000;
        System.out.println("Тестирование ArrayList:");
        testListPerformance(new ArrayList<>(), TEST_SIZE);
        System.out.println("\nТестирование LinkedList:");
        testListPerformance(new LinkedList<>(), TEST_SIZE);
    }

    private static void testListPerformance(List<Integer> list, int testSize) {
        long startTime = System.nanoTime();
        for (int i = 0; i < testSize; i++) {
            list.add(i);
        }
        long endTime = System.nanoTime();
        System.out.printf("add: %d раз, время: %.2f мс%n", testSize, (endTime - startTime) / 1_000_000.0);
        startTime = System.nanoTime();
        for (int i = 0; i < testSize; i++) {
            list.get(i);
        }
        endTime = System.nanoTime();
        System.out.printf("get: %d раз, время: %.2f мс%n", testSize, (endTime - startTime) / 1_000_000.0);
        startTime = System.nanoTime();
        for (int i = testSize - 1; i >= 0; i--) {
            list.remove(i);
        }
        endTime = System.nanoTime();
        System.out.printf("remove: %d раз, время: %.2f мс%n", testSize, (endTime - startTime) / 1_000_000.0);
    }
}
