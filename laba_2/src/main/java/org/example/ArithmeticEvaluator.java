package org.example;

/**
 * Класс для вычисления арифметических выражений.
 * Поддерживает основные математические операции: +, -, *, /, ^ (степень),
 * а также скобки и унарные плюс/минус.
 *
 */
public class ArithmeticEvaluator {
    private int pos = -1; // Текущая позиция в строке
    private int ch; // Текущий символ
    private String str; // Строка с выражением

    /**
     * Вычисляет значение арифметического выражения.
     *
     * @param expression строка с математическим выражением
     * @return результат вычисления
     * @throws RuntimeException если выражение содержит синтаксические ошибки
     *
     * @see #parseExpression()
     * @see #parseTerm()
     * @see #parseFactor()
     */
    public double evaluate(String expression) {
        this.str = expression;
        nextChar();
        double x = parseExpression();
        if (pos < str.length()) {
            throw new RuntimeException("Неожиданный символ: " + (char)ch);
        }
        return x;
    }

    /**
     * Переходит к следующему символу выражения.
     */
    private void nextChar() {
        ch = (++pos < str.length()) ? str.charAt(pos) : -1;
    }

    /**
     * Проверяет и пропускает указанный символ, если он встречается.
     *
     * @param charToEat символ, который нужно проверить
     * @return true если символ был найден и пропущен, false в противном случае
     */
    private boolean eat(int charToEat) {
        while (ch == ' ') nextChar();
        if (ch == charToEat) {
            nextChar();
            return true;
        }
        return false;
    }

    /**
     * Разбирает и вычисляет выражение уровня сложения/вычитания.
     *
     * @return результат вычисления выражения
     * @throws RuntimeException при обнаружении синтаксической ошибки
     */
    private double parseExpression() {
        double x = parseTerm();
        while (true) {
            if (eat('+')) x += parseTerm();
            else if (eat('-')) x -= parseTerm();
            else return x;
        }
    }

    /**
     * Разбирает и вычисляет выражение уровня умножения/деления.
     *
     * @return результат вычисления выражения
     * @throws RuntimeException при обнаружении синтаксической ошибки
     */
    private double parseTerm() {
        double x = parseFactor();
        while (true) {
            if (eat('*')) x *= parseFactor();
            else if (eat('/')) x /= parseFactor();
            else return x;
        }
    }

    /**
     * Разбирает и вычисляет элементарные выражения (числа, скобки, унарные операции).
     *
     * @return результат вычисления выражения
     * @throws RuntimeException при обнаружении синтаксической ошибки
     */
    private double parseFactor() {
        if (eat('+')) return parseFactor();
        if (eat('-')) return -parseFactor();

        double x;
        int startPos = this.pos;

        if (eat('(')) {
            x = parseExpression();
            if (!eat(')')) throw new RuntimeException("Отсутствует закрывающая скобка");
        } else if ((ch >= '0' && ch <= '9') || ch == '.') {
            while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
            x = Double.parseDouble(str.substring(startPos, this.pos));
        } else {
            throw new RuntimeException("Неожиданный символ: " + (char)ch);
        }

        if (eat('^')) x = Math.pow(x, parseFactor());
        return x;
    }
}