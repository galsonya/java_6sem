package org.example;

public class Main {
    public static void main(String[] args) {
        Container container = new Container();
        container.add(10);
        container.add(20);
        container.add(30);
        System.out.println("Размер: " + container.size());
        System.out.println("Первый элемент: " + container.get(0));
        System.out.println("Последний элемент: " + container.get(container.size()-1));
        container.remove(1);
        System.out.println("После удаления второго элемента: " + container.get(1));
    }
}