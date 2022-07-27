package employee;

import employee.pojo.Engineer;
import employee.pojo.Worker;

import java.util.Scanner;

public class Manage {
    Scanner sc = new Scanner(System.in);

    public void add() {
        showOption();
        String option = sc.nextLine();
        Worker w = new Worker();
        Engineer e = new Engineer();
        switch (option) {
            case "1":
                w.add();
                break;
            case "2":
                e.add();
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
