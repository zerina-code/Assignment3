package org.example;

public class EmployeeBuilder {
    private String fullName;
    private String department;
    private int hourlyRate;
    private int hours;
    private int baseSalary;
    private int bonus;
    private int commission;

    public EmployeeBuilder withFullName(String fullName) {
        this.fullName=fullName;
        return this;
    }
    public EmployeeBuilder withDepartment(String department) {
        this.department=department;
        return this;
    }
    public EmployeeBuilder withHourlyRate(int hourlyRate) {
        this.hourlyRate=hourlyRate;
        return this;
    }
    public EmployeeBuilder withHours(int hours) {
        this.hours=hours;
        return this;
    }
    public EmployeeBuilder withBaseSalary(int baseSalary) {
        this.baseSalary=baseSalary;
        return this;
    }
    public EmployeeBuilder withBonus(int bonus) {
        this.bonus=bonus;
        return this;
    }
    public EmployeeBuilder withCommission(int commission) {
        this.commission=commission;
        return this;
    }
    public Employee buildManager() {
        return new Manager(fullName,department,baseSalary,bonus);
    }
    public Employee buildFreelancer() {
        return new Freelancer(fullName,department,hourlyRate,hours);
    }
    public Employee buildIntern(){
        return new Intern(fullName,department);
    }
    public Worker buildWorker() {
        return new Worker(fullName,department,baseSalary,hourlyRate,hours);
    }
    public Employee buildSales() {
        return new Sales(fullName,department,baseSalary,commission);
    }
}
