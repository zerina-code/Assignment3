package org.example;

public class Freelancer extends Employee {
    private int hourlyRate;
    private int hours;

    Freelancer(String fullName, String department, int hours, int hourlyRate) {
        super(fullName, department);
        this.hourlyRate=hourlyRate; this.hours=hours;
    }
    @Override
    public int calculateSalary() {
        return hours*hourlyRate;
    }
    @Override
    public String toString() {
        return "Freelancer: " + getFullName() + ", department: " + getDepartment() + ", salary: " + calculateSalary();
    }
}