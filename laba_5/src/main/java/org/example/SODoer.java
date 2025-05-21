package org.example;

/**
 * Реализация интерфейса {@link SomeOtherInterface}, выводящая "C".
 */
public class SODoer implements SomeOtherInterface {
    @Override
    public void doSomeOther() {
        System.out.println("C");
    }
}
