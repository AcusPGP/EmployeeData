package employee;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Worker extends Person {
    Scanner sc = new Scanner(System.in);
    String name;
    int id;
    int age;
    String address;
    int type = 1;

    public Worker() {
        super();
    }

    public void addWorker() {
        System.out.println(" ");
        name = inputName();
        id = intputId();
        age = inputAge();
        address = inputAddress();
        System.out.println(" ");
        String text = id + " " + name + " " + age + " " + address + " " + type;
        try {
            FileWriter fWriter = new FileWriter("/Users/macbook/OOPProjects/Employee_Data/demo.txt");
            fWriter.write(text);
            System.out.println("File is created successfully with the data.");
            fWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String inputName() {
        System.out.print("Input the name: ");
        return sc.nextLine();
    }

    public int intputId() {
        System.out.print("Input the id: ");
        while (true) {
            try {
                int num1 = Integer.parseInt(sc.nextLine());
                return num1;
            } catch (NumberFormatException e) {
                System.out.println("Invalid! Input the id again. ");
            }
        }
    }

    public int inputAge() {
        System.out.print("Input the age: ");
        while (true) {
            try {
                int num2 = Integer.parseInt(sc.nextLine());
                if (num2 < 0 && num2 > 100) {
                    throw new NumberFormatException();
                }
                return num2;
            } catch (NumberFormatException e) {
                System.out.println("Invalid! Input the age again. ");
            }
        }
    }

    public String inputAddress() {
        System.out.print("Input the address: ");
        return sc.nextLine();
    }
}
