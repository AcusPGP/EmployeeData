package employee;

import employee.pojo.utils.EmployeeConstants;

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
                    System.out.println("");
                    System.out.println(EmployeeConstants.INVALID_OUTPUT);
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
            File file = new File(EmployeeConstants.LIST_PATH);
            if (file.createNewFile()) {
                FileWriter writer = new FileWriter(EmployeeConstants.LIST_PATH);
                writer.write(String.format("%5s %15s %5s %15s %10s %10s", "EMPLOYEE ID", "NAME", "AGE", "ADDRESS", "TYPE", "LEVEL/DEGREE") + "\n");
                writer.close();
            }
        } catch (IOException e) {
            System.out.println(EmployeeConstants.ERROR_OCCURRED);
            e.printStackTrace();
        }
    }

    static void showMenu() {
        System.out.println("----------Menu----------");
        System.out.println(EmployeeConstants.ADD);
        System.out.println(EmployeeConstants.SHOW);
        System.out.println(EmployeeConstants.EDIT_UPDATE);
        System.out.println(EmployeeConstants.DELETE);
        System.out.println(EmployeeConstants.EXIT);
        System.out.println("------------------------");
        System.out.print(EmployeeConstants.CHOOSE_FUNCTION);
    }
}
