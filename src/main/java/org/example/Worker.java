package org.example;

public class Worker extends Employee{
    private int baseSalary;
    private int hourlyRate;
    private int hours;

    Worker(String fullName, String department,int baseSalary, int hours, int hourlyRate) {
        super(fullName, department);
        this.baseSalary=baseSalary;
        this.hourlyRate=hourlyRate;
        this.hours=hours;
    }
    @Override
    public int calculateSalary(){
        return baseSalary + (hours*hourlyRate);
    }

    @Override
    public String toString() {
        return "Worker: " + getFullName() + ", department: " + getDepartment() + ", salary: " + calculateSalary();
    }
}