package employee;

import employee.service.BaseService;
import employee.service.EngineerServiceImpl;
import employee.service.WorkerServiceImpl;

import javax.swing.*;
import java.io.*;
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

    private static Scanner x;

    public void delete(int id) {
        String tempFile = "temp.txt";
        File oldFile = new File("/Users/macbook/OOPProjects/Employee_Data/list.txt");
        File newFile = new File(tempFile);
        String ID;
        String name;
        String age;
        String address;
        String type;
        try {
            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            x = new Scanner(new File("/Users/macbook/OOPProjects/Employee_Data/list.txt"));
            x.useDelimiter("[,]");

            while (x.hasNext()) {
                ID = x.next();
                name = x.next();
                age = x.next();
                address = x.next();
                type = x.next();
                if (!ID.equals(id)) {
                    pw.println(ID + " " + name + " " + age + " " + address + " " + type);
                }
            }
            x.close();
            pw.flush();
            pw.close();
            oldFile.delete();
            File list = new File("/Users/macbook/OOPProjects/Employee_Data/list.txt");
            newFile.renameTo(list);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error");
        }
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
