package org.example;

import org.example.AutoInjectable;
import org.example.SomeInterface;
import org.example.SomeOtherInterface;

public class SomeBean {
    @AutoInjectable
    public SomeInterface field1;

    @AutoInjectable
    public SomeOtherInterface field2;

    public void foo() {
        field1.doSomething();
        field2.doSomeOther();
    }
}