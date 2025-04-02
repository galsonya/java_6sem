package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArithmeticEvaluator evaluator = new ArithmeticEvaluator();

        System.out.println("Advanced Arithmetic Calculator");
        System.out.println("Supported operations: + - * / ^ ( )");
        System.out.println("Example: (3.5 + 2) * 4^2");
        System.out.println("Enter 'exit' to quit\n");

        while (true) {
            System.out.print("Enter expression: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            try {
                // Удаляем все пробелы и заменяем запятые на точки
                String processedInput = input.replaceAll("\\s+", "").replace(',', '.');
                double result = evaluator.evaluate(processedInput);
                System.out.println("Result: " + result);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Please enter valid expression like: 2*(3+4)");
            }
        }

        scanner.close();
    }
}