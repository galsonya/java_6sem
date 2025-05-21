package org.example;

import org.example.Injector;
import org.example.SomeBean;

public class Main {
    public static void main(String[] args) throws Exception {
        SomeBean bean = new Injector().inject(new SomeBean());
        bean.foo();
    }
}