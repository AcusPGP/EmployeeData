package employee.service;

import java.io.*;
import java.util.Scanner;

public abstract class BaseEmployeeServiceImpl implements BaseService {

    Scanner sc = new Scanner(System.in);

    public void edit() throws IOException {
        System.out.print("Please input an Id to edit: ");
        String id = sc.nextLine();
        File file = new File("/Users/macbook/OOPProjects/Employee_Data/list.txt");
        File temp = new File("/Users/macbook/OOPProjects/Employee_Data/temp.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
        String curentLine;
        while ((curentLine = reader.readLine()) != null) {
            String[] array = curentLine.split("@");
            if (array[0].equals(id)) {
                showEditFunctions();
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


    public void delete() throws IOException {
        System.out.print("Please choose an Id to delete: ");
        String id = sc.nextLine().trim();
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

    static void showEditFunctions() {
        System.out.println("Edit Functions");
        System.out.println("1. Change the name.");
        System.out.println("2. Change the age.");
        System.out.println("3. Change the address.");
        System.out.println("4. Change the type employee.");
        System.out.print("Please choose an option: ");
    }
}
