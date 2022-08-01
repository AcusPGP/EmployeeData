package employee;

import employee.service.BaseService;
import employee.service.EngineerServiceImpl;
import employee.service.WorkerServiceImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Manage {

    public static final String FILE_PATH = "/Users/macbook/OOPProjects/Employee_Data/list.txt";

    Scanner sc = new Scanner(System.in);

    public void add() {
        showOptionAdd();
        String option = sc.nextLine().trim();
        switch (option) {
            case "1":
                BaseService worker = new WorkerServiceImpl();
                worker.add();
                break;
            case "2":
                BaseService engineer = new EngineerServiceImpl();
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

    public void delete(String id) throws IOException {
        File inputF = new File("/Users/macbook/OOPProjects/Employee_Data/list.txt");
        File tempF = new File("/Users/macbook/OOPProjects/Employee_Data/temp.txt");
        BufferedReader reader = new BufferedReader(new FileReader(inputF));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempF));
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            String trimmedLine = currentLine.trim();
            String[] array = trimmedLine.split("@");
            if (array[0].equals(id)) continue;
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();
        tempF.renameTo(inputF);
        System.out.println("Delete successful!" + "\n");
    }

    static void showOptionAdd() {
        System.out.println(" ");
        System.out.println("------Job Position------");
        System.out.println("1. Worker.");
        System.out.println("2. Engineer.");
        System.out.println("------------------------");
        System.out.print("Please choose an option: ");
    }
}

