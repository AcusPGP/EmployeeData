package employee;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        getInput();
    }

    public static void getInput() throws Exception {
        Scanner sc = new Scanner(System.in);
        String input;
        boolean exit = false;
        Manage employee = new Manage();
        showMenu();
        createTableListFile();
        while (true) {
            input = sc.nextLine().trim();
            switch (input) {
                case "1" -> employee.add();
                case "2" -> employee.show();
                case "3" -> {
                    employee.show();
                    employee.edit();
                }
                case "4" -> {
                    employee.show();
                    employee.delete();
                }
                case "5" -> {
                    System.out.println("EXIT THE PROGRAM");
                    exit = true;
                }
                default -> {
                    System.out.println(" ");
                    System.out.println("Invalid! Please choose an option in the below menu: ");
                }
            }
            if (exit) {
                break;
            } else {
                showMenu();
            }
        }
    }

    public static void createTableListFile() {
        try {
            File file = new File("/Users/macbook/OOPProjects/Employee_Data/table_list.txt");
            if (file.createNewFile()) {
                FileWriter writer = new FileWriter("/Users/macbook/OOPProjects/Employee_Data/table_list.txt");
                writer.write(String.format("%5s %15s %5s %15s %10s %10s", "EMPLOYEE ID", "NAME", "AGE", "ADDRESS", "TYPE", "LEVEL/DEGREE") + "\n");
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    static void showMenu() {
        System.out.println("----------Menu----------");
        System.out.println("1. Add an employee.");
        System.out.println("2. Show the list of employees.");
        System.out.println("3. Edit and Update the employee's information.");
        System.out.println("4. Delete the employee's information.");
        System.out.println("5. Exit. ");
        System.out.println("------------------------");
        System.out.print("Please choose an option: ");
    }
}
