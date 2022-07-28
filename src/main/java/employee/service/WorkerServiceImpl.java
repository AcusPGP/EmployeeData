package employee.service;

import employee.Manage;
import employee.pojo.Worker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WorkerServiceImpl implements BaseService<Worker> {
    Scanner sc = new Scanner(System.in);

    @Override
    public void add() {
        System.out.println(" ");
        int id = inputId();
        String name = inputName();
        int age = inputAge();
        String address = inputAddress();
        String type = "worker";
        Worker wo = new Worker(id, name, age, address, type);
        addToF(wo);
    }

    private boolean checkFileExist() {
        File file = new File(Manage.FILE_PATH);
        return file.exists();
    }

    private void addToF(Worker wo) {
        FileWriter myList = null;
        try {
            if (checkFileExist()) {
                myList = new FileWriter(Manage.FILE_PATH, true);
                myList.write(wo.toString() + "\n");
            } else {
                myList = new FileWriter(Manage.FILE_PATH);
                myList.write(wo.toString() + "\n");
            }
            myList.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public int inputId() {
        System.out.print("Input the id: ");
        try {
            int id = Integer.parseInt(sc.nextLine());
            return id;
        } catch (NumberFormatException e) {
            System.out.println("Invalid! Please input the id again. ");
            return inputId();
        }
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
    public void delete(Worker object) {

    }
}
