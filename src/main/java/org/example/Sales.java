package org.example;

public class Sales extends Employee {
    private int baseSalary;
    private int commission;

    Sales(String fullName, String department, int baseSalary, int commission) {
        super(fullName,department);
        this.baseSalary=baseSalary; this.commission=commission;
    }
    @Override
    public int calculateSalary() { return baseSalary + commission;}

    @Override
    public String toString() {
        return "Sales: " + getFullName() + ", department: " + getDepartment() + ", salary: " + calculateSalary();
    }
}