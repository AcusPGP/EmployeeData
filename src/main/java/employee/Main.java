package employee;

import employee.util.EmployeeConstants;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        getInput();
    }

    public static void getInput() throws Exception {
        Scanner sc = new Scanner(System.in);
        String input, id;
        boolean exit = false;
        Manage employee = new Manage();
        showMenu();
        while (true) {
            input = sc.nextLine().trim();
            switch (input) {
                case "1":
                    employee.add();
                    break;
                case "2":
                    employee.show();
                    break;
                case "3":
                    employee.show();
                    System.out.print(EmployeeConstants.MSG_EDIT_ID);
                    id = sc.nextLine().trim();
                    employee.edit(id);
                    break;
                case "4":
                    employee.show();
                    System.out.print(EmployeeConstants.MSG_DEL_ID);
                    id = sc.nextLine().trim();
                    employee.delete(id);
                    break;
                case "5":
                    System.out.println(EmployeeConstants.MSG_EXIT_PROGRAM);
                    exit = true;
                    break;
                default:
                    System.out.println(" ");
                    System.out.println("Invalid! Please choose an option in the below menu: ");
            }
            if (exit) {
                break;
            } else {
                showMenu();
            }
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
