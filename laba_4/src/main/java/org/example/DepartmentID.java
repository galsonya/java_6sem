package org.example;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Класс, представляющий идентификатор отдела.
 * Каждый объект DepartmentID имеет уникальный идентификатор и имя отдела.
 */
public class DepartmentID {
    private static final AtomicInteger nextId = new AtomicInteger(1);
    private final int id;
    private final String name;

    /**
     * Конструктор класса DepartmentID.
     *
     * @param name Название отдела.
     */
    public DepartmentID(String name) {
        this.id = nextId.getAndIncrement();
        this.name = name;
    }

    /**
     * Получает уникальный идентификатор отдела.
     *
     * @return Уникальный идентификатор отдела.
     */
    public int getId() {
        return id;
    }

    /**
     * Получает название отдела.
     *
     * @return Название отдела.
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает строковое представление объекта DepartmentID.
     *
     * @return Строка, представляющая объект DepartmentID.
     */
    @Override
    public String toString() {
        return "Department{id=" + id + ", name='" + name + "'}";
    }
}