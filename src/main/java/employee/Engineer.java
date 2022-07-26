package employee;

import java.util.Scanner;

public class Engineer extends Person {
    Scanner sc = new Scanner(System.in);

    public void add() {
        int id = inputId();
        String name = inputName();
        int age = inputAge();
        String address = inputAddress();
        String type = "engineer";
        Person engineer = new Person(id, name, age, address, type);
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

}
