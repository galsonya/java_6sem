package org.example;

import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionEvaluator {
    public double evaluate(String expr, Map<String, Double> variables, Scanner scanner) {
        expr = expr.replaceAll("\\s+", "").replace(',', '.');
        expr = processFunctions(expr, variables, scanner);
        expr = processVariables(expr, variables, scanner);
        return evaluateArithmeticExpression(expr);
    }

    private String processFunctions(String expr, Map<String, Double> variables, Scanner scanner) {
        Pattern funcPattern = Pattern.compile("(sin|cos|tan|log|sqrt)\\(([^)]+)\\)");
        Matcher matcher = funcPattern.matcher(expr);

        while (matcher.find()) {
            String func = matcher.group(1);
            String argStr = matcher.group(2);
            double argValue = argStr.matches("[a-zA-Z]+")
                    ? getVariableValue(argStr, variables, scanner)
                    : Double.parseDouble(argStr);

            double result = applyFunction(func, argValue);
            expr = expr.replace(matcher.group(), String.valueOf(result));
            matcher = funcPattern.matcher(expr);
        }
        return expr;
    }

    private String processVariables(String expr, Map<String, Double> variables, Scanner scanner) {
        Pattern varPattern = Pattern.compile("[a-zA-Z]+");
        Matcher matcher = varPattern.matcher(expr);

        while (matcher.find()) {
            String varName = matcher.group();
            double value = getVariableValue(varName, variables, scanner);
            expr = expr.replace(varName, String.valueOf(value));
            matcher = varPattern.matcher(expr);
        }
        return expr;
    }

    private double getVariableValue(String varName, Map<String, Double> variables, Scanner scanner) {
        if (!variables.containsKey(varName)) {
            System.out.print("Введите значение для " + varName + ": ");
            double value = scanner.nextDouble();
            scanner.nextLine();
            variables.put(varName, value);
        }
        return variables.get(varName);
    }

    private double applyFunction(String func, double arg) {
        switch (func) {
            case "sin":
                return roundTrigResult(Math.sin(Math.toRadians(arg)));
            case "cos":
                return roundTrigResult(Math.cos(Math.toRadians(arg)));
            case "tan":
                return roundTrigResult(Math.tan(Math.toRadians(arg)));
            case "log":
                return Math.log10(arg);
            case "sqrt":
                return Math.sqrt(arg);
            default:
                throw new IllegalArgumentException("Неизвестная функция: " + func);
        }
    }

    private double roundTrigResult(double value) {
        // Округляем до 10 знаков после запятой
        double rounded = Math.round(value * 1e10) / 1e10;

        // Для случаев, когда значение очень близко к 0.5, 0.707 и т.д.
        double[] commonValues = {0, 0.5, 0.707106781, 0.866025403, 1};
        for (double common : commonValues) {
            if (Math.abs(rounded - common) < 1e-9) {
                return common;
            }
        }

        return rounded;
    }

    private double evaluateArithmeticExpression(String expr) {
        return new ArithmeticEvaluator().evaluate(expr);
    }
}