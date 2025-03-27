package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Container container = new Container();

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Добавить элемент");
            System.out.println("2. Получить элемент по индексу");
            System.out.println("3. Удалить элемент по индексу");
            System.out.println("4. Проверить, пуст ли контейнер");
            System.out.println("5. Получить размер контейнера");
            System.out.println("6. Вывести все элементы контейнера");
            System.out.println("7. Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Введите число для добавления: ");
                    int num = scanner.nextInt();
                    container.add(num);
                    System.out.println("Элемент " + num + " добавлен.");
                    break;

                case 2:
                    if (container.isEmpty()) {
                        System.out.println("Контейнер пуст!");
                        break;
                    }
                    System.out.print("Введите индекс: ");
                    int getIndex = scanner.nextInt();
                    try {
                        int element = container.get(getIndex);
                        System.out.println("Элемент с индексом " + getIndex + ": " + element);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    break;

                case 3:
                    if (container.isEmpty()) {
                        System.out.println("Контейнер пуст!");
                        break;
                    }
                    System.out.print("Введите индекс для удаления: ");
                    int removeIndex = scanner.nextInt();
                    try {
                        container.remove(removeIndex);
                        System.out.println("Элемент с индексом " + removeIndex + " удален.");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.println("Контейнер " + (container.isEmpty() ? "пуст" : "не пуст"));
                    break;

                case 5:
                    System.out.println("Размер контейнера: " + container.size());
                    break;

                case 6:
                    if (container.isEmpty()) {
                        System.out.println("Контейнер пуст!");
                        break;
                    }
                    System.out.println("Элементы контейнера:");
                    for (int i = 0; i < container.size(); i++) {
                        System.out.println("[" + i + "] = " + container.get(i));
                    }
                    break;

                case 7:
                    System.out.println("Выход из программы.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }
}