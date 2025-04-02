package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpressionEvaluator evaluator = new ExpressionEvaluator();

        System.out.println("Simple Calculator");
        System.out.println("Supported functions: sin, cos, tan, log, sqrt");
        System.out.println("Example: sin(30) + 2*(3+4)");
        System.out.println("Enter 'exit' to quit");

        while (true) {
            System.out.print("Enter expression: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            try {
                // Теперь передаем только само выражение (1 аргумент)
                double result = evaluator.evaluate(input);
                System.out.println("Result: " + result);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}