package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Calculator {
    private final Scanner scanner = new Scanner(System.in).useLocale(java.util.Locale.US);
    private final ExpressionEvaluator evaluator = new ExpressionEvaluator();

    public void run() {
        System.out.println("Доступные функции: sin, cos, tan, log, sqrt");
        System.out.println("Введите 'exit' для выхода.");

        while (true) {
            Map<String, Double> variables = new HashMap<>();
            System.out.print("Введите выражение: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            try {
                double result = evaluator.evaluate(input, variables, scanner);
                System.out.println("Результат: " + result);
            } catch (Exception e) {
                System.err.println("Ошибка: " + e.getMessage());
            }
        }
        scanner.close();
    }
}