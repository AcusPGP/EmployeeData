package employee;

import java.util.Scanner;

public class Manage {
    Scanner sc = new Scanner(System.in);

    public void add() {
        Worker worker = new Worker();
        Engineer engineer = new Engineer();
        showOption();
        String option = sc.nextLine();
        switch (option) {
            case "1":
                worker.addWorker();
                break;
            case "2":
                engineer.addEngineer();
                break;
            default:
                System.out.println(" ");
                System.out.println("Invalid! Please choose an option in the below menu: ");
        }
    }

    static void showOption() {
        System.out.println(" ");
        System.out.println("------Job Position------");
        System.out.println("1. Worker.");
        System.out.println("2. Engineer.");
        System.out.println("------------------------");
        System.out.print("Please choose an option: ");
    }
}
