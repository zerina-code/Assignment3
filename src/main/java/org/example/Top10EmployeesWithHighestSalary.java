package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Top10EmployeesWithHighestSalary implements Runnable {
    private ArrayList<Employee> allEmployees = new ArrayList<>();

    Top10EmployeesWithHighestSalary(ArrayList<Manager> managers,
                                    ArrayList<Worker> workers,
                                    ArrayList<Sales> sales,
                                    ArrayList<Freelancer> freelancers,
                                    ArrayList<Intern> interns) {
        allEmployees.addAll(managers);
        allEmployees.addAll(workers);
        allEmployees.addAll(sales);
        allEmployees.addAll(freelancers);
        allEmployees.addAll(interns);
    }


    @Override
    public void run() {
        String content = "Top 10 employees with highest salary: " + "\n\n";

        List<Employee> top10Employees = allEmployees.stream()
                .sorted((e1,e2)->e2.calculateSalary()-e1.calculateSalary())
                .limit(10)
                .collect(Collectors.toList());

        int rank = 1;
        for(Employee e : top10Employees) {
            content+= rank + "." + e.getFullName() + " - Salary: " + e.calculateSalary() + "\n";
            rank++;
        }
        try {
            writeFile("report1.txt", content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void writeFile(String fileName, String content) throws IOException  {
        FileWriter fw = new FileWriter(fileName);
        fw.write(content);
        fw.close();
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
}