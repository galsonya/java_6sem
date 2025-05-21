package org.example;

import org.example.AutoInjectable;
import org.example.SomeInterface;
import org.example.SomeOtherInterface;

/**
 * Класс, представляющий объект, в который будут внедрены зависимости.
 * Содержит поля, помеченные аннотацией {@link AutoInjectable}.
 */
public class SomeBean {
    @AutoInjectable
    public SomeInterface field1;

    @AutoInjectable
    public SomeOtherInterface field2;

    /**
     * Метод, который вызывает методы внедренных зависимостей.
     */
    public void foo() {
        field1.doSomething();
        field2.doSomeOther();
    }
}
