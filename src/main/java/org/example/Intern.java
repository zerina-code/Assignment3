package org.example;

public class Intern extends Employee{
    Intern(String fullName, String department) {
        super(fullName, department);
    }
    @Override
    public int calculateSalary(){return 0;}

    @Override
    public String toString(){
        return "Intern: " + getFullName() + ", department: " + getDepartment();
    }
}