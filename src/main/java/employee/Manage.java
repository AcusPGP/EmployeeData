package employee;

import employee.service.BaseService;
import employee.service.engineer.EngineerServiceImpl;
import employee.service.worker.WorkerServiceImpl;

import java.io.*;
import java.util.Scanner;

public class Manage {

    public static final String FILE_PATH = "/Users/macbook/OOPProjects/Employee_Data/list.txt";

    Scanner sc = new Scanner(System.in);

    public void add() {
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
        System.out.println(" ");
        File file = new File("/Users/macbook/OOPProjects/Employee_Data/list.txt");
        Scanner read = new Scanner(file);
        while (read.hasNextLine()) {
            System.out.println(read.nextLine());
        }
        System.out.println("\n");
    }

    public void edit(String id) throws IOException {
        File file = new File("/Users/macbook/OOPProjects/Employee_Data/list.txt");
        File temp = new File("/Users/macbook/OOPProjects/Employee_Data/temp.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
        String curentLine;
        while ((curentLine = reader.readLine()) != null) {
            String[] array = curentLine.split("@");
            if (array[0].equals(id)) {
                showOptionEdit();
                String option = sc.nextLine().trim();
                String nAME, aDDRESS, tYPE;
                int iD, aGE;
                switch (option) {
                    case "1" -> {
                        System.out.println("The current name is " + array[1]);
                        iD = Integer.parseInt(array[0]);
                        aGE = Integer.parseInt(array[2]);
                        aDDRESS = array[3];
                        tYPE = array[4];
                        System.out.print("Input a new name: ");
                        nAME = sc.nextLine().trim();
                        writer.write(iD + "@" + nAME + "@" + aGE + "@" + aDDRESS + "@" + tYPE);
                        writer.close();
                    }
                    case "2" -> {
                        System.out.println("The current age is " + array[2]);
                        iD = Integer.parseInt(array[0]);
                        nAME = array[1];
                        aDDRESS = array[3];
                        tYPE = array[4];
                        System.out.print("Input a new age: ");
                        aGE = Integer.parseInt(sc.nextLine().trim());
                        writer.write(iD + "@" + nAME + "@" + aGE + "@" + aDDRESS + "@" + tYPE);
                        writer.close();
                    }
                    case "3" -> {
                        System.out.println("The current address is " + array[3]);
                        iD = Integer.parseInt(array[0]);
                        nAME = array[1];
                        aGE = Integer.parseInt(array[2]);
                        tYPE = array[4];
                        System.out.print("Input a new address: ");
                        aDDRESS = sc.nextLine();
                        writer.write(iD + "@" + nAME + "@" + aGE + "@" + aDDRESS + "@" + tYPE);
                        writer.close();
                    }
                    case "4" -> {
                        System.out.println("The current type is " + array[4]);
                        iD = Integer.parseInt(array[0]);
                        nAME = array[1];
                        aGE = Integer.parseInt(array[2]);
                        aDDRESS = array[3];
                        System.out.print("Input a new type: ");
                        tYPE = sc.nextLine();
                        writer.write(iD + "@" + nAME + "@" + aGE + "@" + aDDRESS + "@" + tYPE);
                        writer.close();
                    }
                    default -> System.out.println("\n" + "Invalid! Please choose an option in the below menu: ");
                }
            } else {
                writer.write(curentLine + System.getProperty("line.separator"));
            }
        }
        writer.close();
        reader.close();
        temp.renameTo(file);
        System.out.println("Change successful!" + "\n");
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

    static void showOptionEdit() {
        System.out.println(" ");
        System.out.println("1. Change the name.");
        System.out.println("2. Change the age.");
        System.out.println("3. Change the address.");
        System.out.println("4. Change the type employee.");
        System.out.print("Please choose an option: ");
    }

}

