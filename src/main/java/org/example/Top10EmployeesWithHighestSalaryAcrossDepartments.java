
package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Top10EmployeesWithHighestSalaryAcrossDepartments implements Runnable {
    private ArrayList<Manager> managers= new ArrayList<>();
    private ArrayList<Worker> workers= new ArrayList<>();
    private ArrayList<Sales> sales= new ArrayList<>();
    private ArrayList<Intern> interns= new ArrayList<>();
    private ArrayList<Freelancer> freelancers= new ArrayList<>();


    Top10EmployeesWithHighestSalaryAcrossDepartments(ArrayList<Manager> managers,
                                                     ArrayList<Worker> workers,
                                                     ArrayList<Sales> sales,
                                                     ArrayList<Intern> interns,
                                                     ArrayList<Freelancer> freelancers) {
        this.managers=managers;
        this.workers=workers;
        this.sales=sales;
        this.interns=interns;
        this.freelancers=freelancers;

    }
    public <T extends Employee> void loadEmployees(ArrayList<T> employees, Map<String, ArrayList<Employee>> employeeMap) {
        for (Employee e : employees) {
            if (e.getDepartment() == null || e.getDepartment().trim().isEmpty()) {
                continue;
            }
            if (!employeeMap.containsKey(e.getDepartment())) {
                employeeMap.put(e.getDepartment(), new ArrayList<>());
            }
            employeeMap.get(e.getDepartment()).add(e);
        }
    }



    public void generateReport() {
        String content = "Top 10 employees across departments: \n\n";
        Map<String, ArrayList<Employee>> employeesMap = new HashMap<>();
        loadEmployees(managers,employeesMap);
        loadEmployees(workers,employeesMap);
        loadEmployees(sales,employeesMap);
        loadEmployees(interns,employeesMap);
        loadEmployees(freelancers,employeesMap);

        for(Map.Entry<String, ArrayList<Employee>> entry : employeesMap.entrySet()) {
            String department = entry.getKey();
            ArrayList<Employee> employees = entry.getValue();

            List<Employee> top10Employees = employees.stream()
                    .sorted((e1,e2)->e2.calculateSalary()-e1.calculateSalary())
                    .limit(10)
                    .collect(Collectors.toList());
            content+="Department: " + department + "\n";

            int rank = 1;
            for(Employee e : top10Employees) {
                content+= rank + "." + e.getFullName() +", Department: " + e.getDepartment() + ", salary: " + e.calculateSalary() + "\n";
                rank++;
            }

            try {
                writeToFile("report2.txt", content);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

    }

    @Override
    public void run() {
        generateReport();

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
