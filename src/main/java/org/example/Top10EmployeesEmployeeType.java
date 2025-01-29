
package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Top10EmployeesEmployeeType implements Runnable {
    private ArrayList<Manager> managers;
    private ArrayList<Worker> workers;
    private ArrayList<Sales> sales;
    private ArrayList<Intern> interns;
    private ArrayList<Freelancer> freelancers;

    Top10EmployeesEmployeeType(ArrayList<Manager> managers, ArrayList<Worker> workers,
                               ArrayList<Sales> sales, ArrayList<Intern> interns,
                               ArrayList<Freelancer> freelancers) {
        this.managers = managers;
        this.workers = workers;
        this.sales = sales;
        this.interns = interns;
        this.freelancers = freelancers;
    }

    public <T extends Employee> void reportGenerate(String employeeType, ArrayList<T> employees, StringBuilder reportContent) {
        reportContent.append("Employee type: ").append(employeeType).append(":\n\n");

        List<Employee> top10Employees = employees.stream()
                .sorted((e1, e2) -> Integer.compare(e2.calculateSalary(), e1.calculateSalary()))
                .limit(10)
                .collect(Collectors.toList());

        int rank = 1;
        for (Employee e : top10Employees) {
            reportContent.append(rank)
                    .append(". ")
                    .append(e.getFullName())
                    .append(" - Salary: ")
                    .append(e.calculateSalary())
                    .append("\n");
            rank++;
        }
        reportContent.append("\n");
    }

    @Override
    public void run() {
        StringBuilder reportContent = new StringBuilder();
        reportContent.append("Top 10 employees with highest salary per employee type:\n\n");

        reportGenerate("Sales", sales, reportContent);
        reportGenerate("Managers", managers, reportContent);
        reportGenerate("Workers", workers, reportContent);
        reportGenerate("Freelancers", freelancers, reportContent);
        reportGenerate("Interns", interns, reportContent);

        try {
            writeToFile("report3.txt", reportContent.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void generate() {
        Thread reportThread = new Thread(this);
        reportThread.start();
        try {
            reportThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeToFile(String fileName, String content) throws IOException {
        FileWriter fw = new FileWriter(fileName);
        fw.write(content);
        fw.close();
    }
}
