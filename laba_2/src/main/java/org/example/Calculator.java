package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Калькулятор для вычисления математических выражений с поддержкой переменных и функций.
 * <p>
 * Калькулятор поддерживает следующие операции:
 * <ul>
 *   <li>Базовые арифметические операции: +, -, *, /</li>
 *   <li>Возведение в степень: ^</li>
 *   <li>Математические функции: sin, cos, tan, log, sqrt</li>
 *   <li>Работу с переменными (например, "x + y")</li>
 *   <li>Скобки для задания приоритета операций</li>
 * </ul>
 *
 * <p>Для тестирования можно использовать метод {@link #setScanner(Scanner)}.
 */
public class Calculator {
    private Scanner scanner = new Scanner(System.in).useLocale(java.util.Locale.US);
    private final ExpressionEvaluator evaluator = new ExpressionEvaluator();

    /**
     * Запускает интерактивный режим работы калькулятора.
     * <p>
     * В этом режиме калькулятор в цикле запрашивает у пользователя выражения для вычисления,
     * пока не будет введена команда "exit".
     */
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

    /**
     * Устанавливает Scanner для ввода данных.
     * <p>
     * Основное применение - тестирование, позволяет заменить стандартный ввод с консоли
     * на заранее подготовленные данные.
     *
     * @param scanner объект Scanner, который будет использоваться для ввода данных
     */
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}