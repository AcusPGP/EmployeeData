package employee.service;


import employee.pojo.Engineer;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class EngineerServiceImpl implements BaseService<Engineer>   {
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

    private void addToF(Engineer en) {
        try {
            FileWriter myList = new FileWriter("/Users/macbook/OOPProjects/Employee_Data/list.txt");
            myList.write(en.toString());
            myList.close();
        } catch (IOException e) {
            System.out.println();
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
