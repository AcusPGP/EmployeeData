package employee;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        getInput();
    }

    static void getInput() {
        Scanner sc = new Scanner(System.in);
        String input;
        boolean exit = false;
        showMenu();
        while (true) {
            input = sc.nextLine();
            switch (input) {
                case "1":
                    System.out.println("Success");
                    break;
                case "2":
                    System.out.println("Success");
                    break;
                case "3":
                    System.out.println("Success");
                    break;
                case "4":
                    System.out.println("Success");
                    break;
                case "5":
                    System.out.println("EXIT THE PROGRAM");
                    exit = true;
                    break;
                default:
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
        System.out.println("5. Exit ");
        System.out.println("------------------------");
        System.out.print("Please choose an option: ");
    }
}
