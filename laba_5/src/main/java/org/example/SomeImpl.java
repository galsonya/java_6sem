package org.example;

/**
 * Реализация интерфейса {@link SomeInterface}, выводящая "A".
 */
public class SomeImpl implements SomeInterface {
    @Override
    public void doSomething() {
        System.out.println("A");
    }
}