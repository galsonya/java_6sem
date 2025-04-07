package org.example;

import java.util.ArrayList;
import java.util.LinkedList;

public class ListPerformanceTest {
    public static void main(String[] args) {
        final int TEST_SIZE = 10000; // Количество операций

        // Заголовок таблицы
        System.out.printf("%-15s %-15s %-15s %-15s%n", "Метод", "Количество", "Время (мс)", "Структура");
        System.out.println("-------------------------------------------------------------");

        // Тестируем ArrayList
        PerformanceTester.testListPerformance(new ArrayList<>(), TEST_SIZE, "ArrayList");

        // Тестируем LinkedList
        PerformanceTester.testListPerformance(new LinkedList<>(), TEST_SIZE, "LinkedList");
    }
}
