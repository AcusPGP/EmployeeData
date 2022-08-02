package employee.service;

import employee.Manage;
import employee.pojo.Engineer;

import java.io.*;
import java.util.Scanner;

public class EngineerServiceImpl implements BaseService<Engineer> {


    Scanner sc = new Scanner(System.in);

    @Override
    public void add()  {
        System.out.println(" ");
        int id = inputId();
        String name = inputName();
        int age = inputAge();
        String address = inputAddress();
        System.out.println(" ");
        String type = "engineer";
        Engineer en = new Engineer(id, name, age, address, type);
        addToF(en);
    }

    private boolean checkFileExist() {
        File file = new File(Manage.FILE_PATH);
        return file.exists();
    }

    private void addToF(Engineer en) {
        FileWriter myList = null;
        try {
            if (checkFileExist()) {
                myList = new FileWriter(Manage.FILE_PATH, true);
                myList.write(en.toString() + "\n");
            } else {
                myList = new FileWriter(Manage.FILE_PATH);
                myList.write(en.toString() + "\n");
            }
            myList.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public int inputId() {
        System.out.print("Input the id: ");
        try {
            String id = sc.nextLine().trim();
            checkId(id);
            return Integer.parseInt(id);
        } catch (NumberFormatException | IOException e) {
            System.out.println("Invalid! Please input the id again. ");
            return inputId();
        }
    }

    public void checkId(String id) throws IOException {
        File check = new File("/Users/macbook/OOPProjects/Employee_Data/list.txt");
        BufferedReader reader = new BufferedReader(new FileReader(check));
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            String trimmedLine = currentLine.trim();
            String[] array = trimmedLine.split("@");
            if (array[0].equals(id)) {
                System.out.println("This ID " + "'" + id + "'" + " is aLready had. Please try another id." + "\n");
                reader.close();
                inputId();
            }
        }
        reader.close();
    }

    public String inputName() {
        System.out.print("Input the name: ");
        return sc.nextLine();
    }

    public int inputAge() {
        System.out.print("Input the age: ");
        try {
            int age = Integer.parseInt(sc.nextLine().trim());
            if (age >= 1 && age <= 100) {
                return age;
            } else {
                System.out.println("Invalid! Please input the age again. ");
                return inputAge();
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid! Please input the age again. ");
            return inputAge();
        }
    }

    public String inputAddress() {
        System.out.print("Input the address: ");
        return sc.nextLine();
    }


    @Override
    public void delete(Engineer object) {

    }
}
