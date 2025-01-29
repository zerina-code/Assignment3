package org.example;

public class Manager extends Employee{
    private int baseSalary;
    private int bonus;

    Manager(String fullName, String department, int baseSalary, int bonus) {
        super(fullName, department);
        this.baseSalary=baseSalary; this.bonus=bonus;
    }

    @Override
    public int calculateSalary() {
        return baseSalary+bonus;
    }
    @Override
    public String toString() {
        return "Manager: " + getFullName() + ", department: " + getDepartment() + ", salary: " + calculateSalary();
    }
}