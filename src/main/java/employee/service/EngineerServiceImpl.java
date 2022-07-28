package employee.service;

import employee.Manage;
import employee.pojo.Engineer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class EngineerServiceImpl implements BaseService<Engineer> {


    Scanner sc = new Scanner(System.in);

    @Override
    public void add() {
        int id = inputId();
        String name = inputName();
        int age = inputAge();
        String address = inputAddress();
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
        while (true) {
            try {
                int id = Integer.parseInt(sc.nextLine());
                return id;
            } catch (NumberFormatException e) {
                System.out.println("Invalid! Please input the id again. ");
            }
        }
    }

    public String inputName() {
        System.out.print("Input the name: ");
        return sc.nextLine();
    }

    public int inputAge() {
        System.out.print("Input the age: ");
        while (true) {
            try {
                int age = Integer.parseInt(sc.nextLine());
                return age;
            } catch (NumberFormatException e) {
                System.out.println("Invalid! Please input the age again. ");
            }
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
