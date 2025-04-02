package org.example;

import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс для вычисления математических выражений с поддержкой функций и переменных.
 * <p>
 * Обрабатывает строковые выражения, содержащие:
 * <ul>
 *   <li>Арифметические операции (через {@link ArithmeticEvaluator})</li>
 *   <li>Математические функции: sin, cos, tan, log (по основанию 10), sqrt</li>
 *   <li>Пользовательские переменные (например, "x + y")</li>
 * </ul>
 */
public class ExpressionEvaluator {

    /**
     * Вычисляет значение математического выражения.
     *
     * @param expr строка с выражением для вычисления
     * @param variables карта для хранения значений переменных
     * @param scanner объект Scanner для ввода значений переменных
     * @return результат вычисления выражения
     * @throws IllegalArgumentException если встречена неизвестная функция
     * @throws RuntimeException при синтаксических ошибках в выражении
     */
    public double evaluate(String expr, Map<String, Double> variables, Scanner scanner) {
        expr = expr.replaceAll("\\s+", "").replace(',', '.');
        expr = processFunctions(expr, variables, scanner);
        expr = processVariables(expr, variables, scanner);
        return evaluateArithmeticExpression(expr);
    }

    /**
     * Обрабатывает математические функции в выражении.
     *
     * @param expr строка выражения
     * @param variables карта переменных
     * @param scanner объект для ввода значений
     * @return выражение с замененными функциями на их значения
     */
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

    /**
     * Заменяет переменные в выражении их значениями.
     *
     * @param expr строка выражения
     * @param variables карта переменных
     * @param scanner объект для ввода новых значений
     * @return выражение с замененными переменными на их значения
     */
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

    /**
     * Получает значение переменной, запрашивая ввод при необходимости.
     *
     * @param varName имя переменной
     * @param variables карта переменных
     * @param scanner объект для ввода значений
     * @return значение переменной
     */
    private double getVariableValue(String varName, Map<String, Double> variables, Scanner scanner) {
        if (!variables.containsKey(varName)) {
            System.out.print("Введите значение для " + varName + ": ");
            double value = scanner.nextDouble();
            scanner.nextLine();
            variables.put(varName, value);
        }
        return variables.get(varName);
    }

    /**
     * Применяет математическую функцию к аргументу.
     *
     * @param func название функции (sin, cos, tan, log, sqrt)
     * @param arg значение аргумента
     * @return результат применения функции
     * @throws IllegalArgumentException если функция неизвестна
     */
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

    /**
     * Округляет результат тригонометрической функции для стандартных значений.
     *
     * @param value исходное значение
     * @return округленное значение (0, 0.5, 0.707... и т.д.)
     */
    private double roundTrigResult(double value) {
        double rounded = Math.round(value * 1e10) / 1e10;
        double[] commonValues = {0, 0.5, 0.707106781, 0.866025403, 1};
        for (double common : commonValues) {
            if (Math.abs(rounded - common) < 1e-9) {
                return common;
            }
        }
        return rounded;
    }

    /**
     * Вычисляет арифметическое выражение через {@link ArithmeticEvaluator}.
     *
     * @param expr строка с арифметическим выражением
     * @return результат вычисления
     */
    private double evaluateArithmeticExpression(String expr) {
        return new ArithmeticEvaluator().evaluate(expr);
    }
}