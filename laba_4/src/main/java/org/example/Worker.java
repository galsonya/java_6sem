package org.example;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Worker {
    private final int id;
    private final String name;
    private final String gender;
    private final DepartmentID department;
    private final double salary;
    private final Date birthDate;

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
    public int getId() { return id; }
    public String getName() { return name; }
    public String getGender() { return gender; }
    public DepartmentID getDepartment() { return department; }
    public double getSalary() { return salary; }
    public Date getBirthDate() { return birthDate; }

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