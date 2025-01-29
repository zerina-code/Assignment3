package org.example;

public abstract class Employee {
    private String fullName;
    private String department;

    public Employee(String fullName, String department) {
        this.fullName = fullName;
        this.department = department;
    }

    public abstract int calculateSalary();

    public String getFullName() {
        return fullName;
    }

    public String getDepartment() {
        return department;
    }
}
