package employee;

import employee.service.BaseService;
import employee.service.EngineerServiceImpl;
import employee.service.WorkerServiceImpl;

import java.io.File;
import java.util.Scanner;

public class Manage {

    public static final String FILE_PATH = "/Users/macbook/OOPProjects/Employee_Data/list.txt";

    Scanner sc = new Scanner(System.in);

    public void add() {
        showOption();
        String option = sc.nextLine().trim();
        switch (option) {
            case "1":
                BaseService worker = new WorkerServiceImpl();
                worker.add();
                break;
            case "2":
                BaseService engineer  = new EngineerServiceImpl();
                engineer.add();
                break;
            default:
                System.out.println(" ");
                System.out.println("Invalid! Please choose an option in the below menu: ");
        }
    }

    public void show() throws Exception {
        System.out.println(" ");
        File file = new File("/Users/macbook/OOPProjects/Employee_Data/list.txt");
        Scanner read = new Scanner(file);
        while (read.hasNextLine()) {
            System.out.println(read.nextLine());
        }
        System.out.println(" ");
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
