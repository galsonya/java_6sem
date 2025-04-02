package org.example;

import org.junit.jupiter.api.*;
import java.io.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTests {
    private ArithmeticEvaluator arithmeticEvaluator;
    private ExpressionEvaluator expressionEvaluator;
    private Calculator calculator;
    private Map<String, Double> variables;
    private final Scanner dummyScanner = new Scanner("0"); // Фиктивный Scanner для тестов

    @BeforeEach
    void setUp() {
        arithmeticEvaluator = new ArithmeticEvaluator();
        expressionEvaluator = new ExpressionEvaluator();
        calculator = new Calculator();
        variables = new HashMap<>();
    }

    @Test
    @DisplayName("Проверка базовых арифметических операций")
    void testArithmeticOperations() {
        assertEquals(5, arithmeticEvaluator.evaluate("2+3"));
    }

    @Test
    @DisplayName("Проверка приоритета операций и скобок")
    void testOperatorPrecedence() {
        assertEquals(14, arithmeticEvaluator.evaluate("2+3*4"));
    }

    @Test
    @DisplayName("Проверка математических функций")
    void testMathFunctions() {
        assertEquals(0.5, expressionEvaluator.evaluate("sin(30)", variables, dummyScanner));
        assertEquals(0.5, expressionEvaluator.evaluate("cos(60)", variables, dummyScanner));
        assertEquals(2, expressionEvaluator.evaluate("log(100)", variables, dummyScanner));
    }

    @Test
    @DisplayName("Проверка обработки переменных")
    void testVariablesProcessing() {
        variables.put("x", 10.0);
        assertEquals(20, expressionEvaluator.evaluate("x*2", variables, dummyScanner));
    }

    @Test
    @DisplayName("Проверка работы калькулятора с простым выражением")
    void testCalculatorSimpleExpression() throws IOException {
        String input = "2+3\nexit\n";
        provideInput(input);

        ByteArrayOutputStream output = captureOutput();
        calculator.run();

        assertTrue(output.toString().contains("Результат: 5.0"));
    }

    @Test
    @DisplayName("Проверка ввода переменных")
    void testCalculatorWithVariables() throws IOException {
        String input = "x+y\n5\n3\nexit\n";
        provideInput(input);

        ByteArrayOutputStream output = captureOutput();
        calculator.run();

        String consoleOutput = output.toString();
        assertTrue(consoleOutput.contains("Введите значение для x"));
        assertTrue(consoleOutput.contains("Введите значение для y"));
        assertTrue(consoleOutput.contains("Результат: 8.0"));
    }

    private void provideInput(String data) {
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        calculator.setScanner(new Scanner(System.in));
        System.setIn(stdin);
    }

    private ByteArrayOutputStream captureOutput() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        return output;
    }

    @AfterEach
    void restoreStreams() {
        System.setIn(System.in);
        System.setOut(System.out);
    }
}