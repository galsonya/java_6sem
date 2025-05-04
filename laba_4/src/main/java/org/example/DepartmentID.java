package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class DepartmentID {
    private static final AtomicInteger nextId = new AtomicInteger(1);
    private final int id;
    private final String name;

    public DepartmentID(String name) {
        this.id = nextId.getAndIncrement();
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "Department{id=" + id + ", name='" + name + "'}";
    }
}