package employee;

import employee.pojo.Person;
import employee.service.BaseService;
import employee.service.engineer.EngineerServiceImpl;
import employee.service.worker.WorkerServiceImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Manage {

    public static final String FILE_PATH = "/Users/macbook/OOPProjects/Employee_Data/list.txt";

    Scanner sc = new Scanner(System.in);

    public void add() throws IOException {
        showOptionAdd();
        String option = sc.nextLine().trim();
        switch (option) {
            case "1" -> {
                BaseService worker = new WorkerServiceImpl();
                worker.add();
            }
            case "2" -> {
                BaseService engineer = new EngineerServiceImpl();
                engineer.add();
            }
            default -> System.out.println("\n" + "Invalid! Please choose an option in the below menu. ");
        }
    }

    public void show() throws Exception {
        showOptionShow();
        String option = sc.nextLine().trim();
        switch (option) {
            case "1" -> {
                BaseService worker = new WorkerServiceImpl();
                worker.show();
                break;
            }
            case "2" -> {
                BaseService engineer = new EngineerServiceImpl();
                engineer.show();
                break;
            }
            case "3" -> {
                List<Person> employee = new ArrayList<>();
                System.out.println("---------------------------------------------------------------------------------------------");
                System.out.printf("%5s %10s %10s %16s %20s %17s", "EMPLOYEE ID", "NAME", "AGE", "ADDRESS", "TYPE", "LEVEL/DEGREE");
                System.out.println();
                System.out.println("---------------------------------------------------------------------------------------------");
                for (Person person : employee) {
                    System.out.format("%7s %14s %7s %18s %25s %13s",person.getId(), person.getName(), person.getAge(), person.getAddress(), person.getType(), person.getLevel());
                    System.out.println();
                }
                System.out.println("---------------------------------------------------------------------------------------------");
                break;
            }
        }
    }

    public void edit() throws IOException {
        showOptionEdit();
        String option = sc.nextLine().trim();
        switch (option) {
            case "1" -> {
                BaseService worker = new WorkerServiceImpl();
                worker.edit();
            }
            case "2" -> {
                BaseService engineer = new EngineerServiceImpl();
                engineer.edit();
            }
            default -> System.out.println("\n" + "Invalid! Please choose an option in the below menu. ");
        }
    }

    public void delete() throws IOException {
        showOptionDelete();
        String option = sc.nextLine().trim();
        switch (option) {
            case "1" -> {
                BaseService worker = new WorkerServiceImpl();
                worker.delete();
            }
            case "2" -> {
                BaseService engineer = new EngineerServiceImpl();
                engineer.delete();
            }
            default -> System.out.println("\n" + "Invalid! Please choose an option in the below menu. ");
        }
    }

    static void showOptionAdd() {
        System.out.println("Job Position");
        System.out.println("1. Worker.");
        System.out.println("2. Engineer.");
        System.out.print("Please choose an option: ");
    }

    static void showOptionShow() {
        System.out.println("Show Options");
        System.out.println("1. Show worker list");
        System.out.println("2. Show engineer list");
        System.out.println("3. Show full list");
        System.out.print("Please choose an option: ");
    }

    static void showOptionEdit() {
        System.out.println("Edit Options");
        System.out.println("1. Change the worker's info by id.");
        System.out.println("2. Change the engineer's info by id.");
        System.out.print("Please choose an option: ");
    }

    static void showOptionDelete() {
        System.out.println("Delete Options");
        System.out.println("1. Delete a worker by id.");
        System.out.println("2. Delete a engineer by id.");
        System.out.print("Please choose an option: ");
    }

}


