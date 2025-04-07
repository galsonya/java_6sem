package org.example;

import java.util.List;

/**
 * Класс PerformanceTester предоставляет методы для тестирования производительности
 * различных реализаций интерфейса List в Java.
 */
public class PerformanceTester {
    /**
     * Тестирует производительность методов добавления, получения и удаления элементов
     * в переданном списке.
     *
     * @param list     Список, производительность которого будет тестироваться.
     * @param testSize Количество элементов, которые будут добавлены, получены и удалены.
     * @param listType Тип списка (например, "ArrayList" или "LinkedList"), который будет выведен в результатах.
     */
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
