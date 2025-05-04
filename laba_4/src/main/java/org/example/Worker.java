package org.example;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Класс, представляющий работника.
 * Каждый объект Worker содержит информацию о работнике, включая его идентификатор, имя, пол, отдел, зарплату и дату рождения.
 */
public class Worker {
    private final int id;
    private final String name;
    private final String gender;
    private final DepartmentID department;
    private final double salary;
    private final Date birthDate;

    /**
     * Конструктор класса Worker.
     *
     * @param id Идентификатор работника.
     * @param name Имя работника.
     * @param gender Пол работника.
     * @param department Отдел, к которому принадлежит работник.
     * @param salary Зарплата работника.
     * @param birthDate Дата рождения работника.
     */
    public Worker(int id, String name, String gender, DepartmentID department,
                  double salary, Date birthDate) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.department = department;
        this.salary = salary;
        this.birthDate = birthDate;
    }

    // Геттеры

    /**
     * Получает идентификатор работника.
     *
     * @return Идентификатор работника.
     */
    public int getId() {
        return id;
    }

    /**
     * Получает имя работника.
     *
     * @return Имя работника.
     */
    public String getName() {
        return name;
    }

    /**
     * Получает пол работника.
     *
     * @return Пол работника.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Получает отдел, к которому принадлежит работник.
     *
     * @return Отдел работника.
     */
    public DepartmentID getDepartment() {
        return department;
    }

    /**
     * Получает зарплату работника.
     *
     * @return Зарплата работника.
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Получает дату рождения работника.
     *
     * @return Дата рождения работника.
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Возвращает строковое представление объекта Worker.
     *
     * @return Строка, представляющая объект Worker.
     */
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return "Worker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", department=" + department +
                ", salary=" + salary +
                ", birthDate=" + sdf.format(birthDate) +
                '}';
    }
}