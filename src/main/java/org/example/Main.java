package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static ArrayList<Manager> loadManagers(String fileName) throws FileNotFoundException {
        File f = new File(fileName);
        Scanner s = new Scanner(f);
        ArrayList<Manager> managers = new ArrayList<>();

        s.nextLine();
        while(s.hasNextLine()) {
            String line = s.nextLine();
            String[] parts = line.split(",");
            String fullName = parts[1] + " " + parts[2];
            String department = parts[3];
            int baseSalary = Integer.parseInt(parts[4].trim());
            int bonus = Integer.parseInt(parts[5].trim());
            Manager m = new Manager(fullName,department,baseSalary,bonus);
            managers.add(m);
        }
        return managers;

    }

    public static ArrayList<Sales> loadSales(String fileName) throws FileNotFoundException {
        File f = new File(fileName);
        Scanner s = new Scanner(f);
        s.nextLine();
        ArrayList<Sales> sales = new ArrayList<>();

        while(s.hasNextLine()) {
            String line = s.nextLine();
            String[] parts = line.split(",");
            String fullName = parts[1] + " " + parts[2];
            String department = parts[3];
            Integer baseSalary = Integer.parseInt(parts[4].trim());
            Integer commission = Integer.parseInt(parts[5].trim());
            Sales sl = new Sales(fullName,department,baseSalary,commission);
            sales.add(sl);
        }
        return sales;
    }
    public static ArrayList<Worker> loadWorkers(String fileName) throws FileNotFoundException {
        File f = new File(fileName);
        Scanner s = new Scanner(f);
        s.nextLine();
        ArrayList<Worker> workers = new ArrayList<>();

        while(s.hasNextLine()) {
            String line = s.nextLine();
            String[] parts = line.split(",");
            String fullName = parts[1] + " " + parts[2];
            String department = parts[3];
            Integer baseSalary = Integer.parseInt(parts[4].trim());
            Integer hours = Integer.parseInt(parts[5].trim());
            Integer hourlyRate = Integer.parseInt(parts[6].trim());
            Worker w = new Worker(fullName,department,baseSalary,hourlyRate,hours);
            workers.add(w);
        }
        return workers;
    }
    public static ArrayList<Freelancer> loadFreelancers(String fileName) throws FileNotFoundException{
        File f = new File(fileName);
        Scanner s = new Scanner(f);
        ArrayList<Freelancer> freelancers = new ArrayList<>();
        s.nextLine();
        while(s.hasNextLine()) {
            String line = s.nextLine();
            String[] parts = line.split(",");
            String fullName = parts[1] + " " + parts[2];
            String department = parts[3];
            Integer hoursworked = Integer.parseInt(parts[4].trim());
            Integer hourlyRate = Integer.parseInt(parts[5].trim());
            Freelancer fl = new Freelancer(fullName,department,hoursworked,hourlyRate);
            freelancers.add(fl);
        }
        return freelancers;
    }

    public static ArrayList<Intern> loadInterns(String fileName) throws FileNotFoundException {
        File f = new File(fileName);
        Scanner s = new Scanner(f);
        ArrayList<Intern> interns = new ArrayList<>();
        s.nextLine();
        while(s.hasNextLine()) {
            String line = s.nextLine();
            String[] parts = line.split(",");
            String fullName = parts[1] + " " + parts[2];
            String department = parts[3];
            Intern i = new Intern(fullName,department);
            interns.add(i);
        }
        return interns;
    }

    public static void main( String[] args ) {
        ArrayList<Manager> managers = new ArrayList<>();
        ArrayList<Worker> workers = new ArrayList<>();
        ArrayList<Sales> sales = new ArrayList<>();
        ArrayList<Freelancer> freelancers = new ArrayList<>();
        ArrayList<Intern> interns = new ArrayList<>();

        try {
            managers = loadManagers("manager.csv");
            workers = loadWorkers("worker.csv");
            sales = loadSales("sales.csv");
            freelancers = loadFreelancers("freelancer.csv");
            interns = loadInterns("intern.csv");
        } catch (FileNotFoundException e) {
            System.out.println("Error loading file");
        }
        Top10EmployeesWithHighestSalary report1 = new Top10EmployeesWithHighestSalary(managers,
                workers, sales, freelancers,interns);
        report1.generate();
        Top10EmployeesWithHighestSalaryAcrossDepartments report2 = new Top10EmployeesWithHighestSalaryAcrossDepartments(managers,
                workers,sales,interns,freelancers);
        report2.generate();
        Top10EmployeesEmployeeType report3 = new Top10EmployeesEmployeeType(managers,workers,sales,interns,freelancers);
        report3.generate();


    }

}
