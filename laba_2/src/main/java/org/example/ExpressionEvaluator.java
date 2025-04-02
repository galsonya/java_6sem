package org.example;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionEvaluator {
    private final Scanner scanner = new Scanner(System.in);

    public double evaluate(String expr) {
        expr = expr.replaceAll("\\s+", "").replace(',', '.');
        expr = processFunctions(expr);
        return new ArithmeticEvaluator().evaluate(expr);
    }

    private String processFunctions(String expr) {
        Pattern pattern = Pattern.compile("(sin|cos|tan|log|sqrt)\\(([^)]+)\\)");
        Matcher matcher = pattern.matcher(expr);

        while (matcher.find()) {
            String func = matcher.group(1);
            double arg = Double.parseDouble(matcher.group(2));
            double result = applyFunction(func, arg);
            expr = expr.replace(matcher.group(), String.valueOf(result));
            matcher = pattern.matcher(expr);
        }

        return expr;
    }

    private double applyFunction(String func, double arg) {
        switch (func) {
            case "sin": return Math.sin(Math.toRadians(arg));
            case "cos": return Math.cos(Math.toRadians(arg));
            case "tan": return Math.tan(Math.toRadians(arg));
            case "log": return Math.log(arg);
            case "sqrt": return Math.sqrt(arg);
            default:
                throw new IllegalArgumentException("Unknown function: " + func);
        }
    }
}