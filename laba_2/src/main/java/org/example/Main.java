// Main.java
package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter simple expression (a + b): ");
        double a = scanner.nextDouble();
        String op = scanner.next();
        double b = scanner.nextDouble();

        double result = 0;
        switch(op) {
            case "+": result = a + b; break;
            case "-": result = a - b; break;
            case "*": result = a * b; break;
            case "/": result = a / b; break;
        }

        System.out.println("Result: " + result);
        scanner.close();
    }
}