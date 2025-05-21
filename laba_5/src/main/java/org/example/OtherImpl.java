package org.example;

/**
 * Реализация интерфейса {@link SomeInterface}, выводящая "B".
 */
public class OtherImpl implements SomeInterface {
    @Override
    public void doSomething() {
        System.out.println("B");
    }
}
