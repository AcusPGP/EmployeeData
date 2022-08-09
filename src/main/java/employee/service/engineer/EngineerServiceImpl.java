package employee.service.engineer;

import employee.Manage;
import employee.pojo.Engineer;
import employee.pojo.Person;
import employee.service.BaseEmployeeServiceImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EngineerServiceImpl extends BaseEmployeeServiceImpl implements EngineerService {

    Scanner sc = new Scanner(System.in);

    @Override
    public void add() throws IOException {
        System.out.println(" ");
        int id = inputId();
        String name = inputName();
        int age = inputAge();
        String address = inputAddress();
        String degree = chooseDegree();
        System.out.println(" ");
        String type = "engineer";
        List<Person> employee = new ArrayList<>();
        employee.add(new Person(id, name, age, address, type, degree));
        Engineer en = new Engineer(id, name, age, address, type, degree);
        addToF(en);
    }

    @Override
    public void show() throws IOException {
        System.out.println("\n");
        File file = new File("/Users/macbook/OOPProjects/Employee_Data/list.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            String[] array = currentLine.split("@");
            if (array[4].equals("engineer")) {
                System.out.println(currentLine + "\n");
            } else {
                continue;
            }
        }
        reader.close();
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

    public int inputId() throws IOException {
        while (true) {
            int suggestId = suggestId();
            System.out.println("Suggest ID: " + suggestId);
            System.out.print("Please enter to get this ID or input a new ID: ");
            String sgId = sc.nextLine().trim();
            String empty = "";
            if (sgId.equals(empty)) {
                return suggestId;
            } else {
                try {
                    String id = sgId;
                    int result = checkId(id);
                    if (result == 0) {
                        return Integer.parseInt(id);
                    }
                } catch (NumberFormatException | IOException e) {
                    System.out.println("Invalid! Please input the id again. ");
                    return inputId();
                }
            }
        }
    }

    public int checkId(String id) throws IOException {
        int result = 0;
        File check = new File("/Users/macbook/OOPProjects/Employee_Data/list.txt");
        if (check.length() == 0) {
            return result;
        } else {
            BufferedReader reader = new BufferedReader(new FileReader(check));
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                String[] array = trimmedLine.split("@");
                if (array[0].equals(id)) {
                    result = -1;
                    System.out.println("This ID " + "'" + id + "'" + " is aLready had. Please try another id." + "\n");
                    break;
                }
            }
            reader.close();
            return result;
        }
    }

    public int suggestId() throws IOException {
        int num = 0;
        File file = new File("/Users/macbook/OOPProjects/Employee_Data/list.txt");
        String currentLine;
        if (file.length() == 0) {
            num = 1;
        } else {
            BufferedReader reader = new BufferedReader(new FileReader("/Users/macbook/OOPProjects/Employee_Data/list.txt"));
            while ((currentLine = reader.readLine()) != null) {
                String[] array = currentLine.split("@");
                for (int i = 1; i > 0; i++) {
                    if (i != Integer.parseInt(array[0])) {
                        num = i;
                        break;
                    }
                }
            }
        }
        return num;
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

    public String chooseDegree() {
        System.out.println("Choose degree for the engineer.");
        System.out.println("1. Front-end");
        System.out.println("2. Back-end");
        System.out.println("3. Full-stack");
        System.out.print("Please choose an option: ");
        String option = sc.nextLine().trim();
        String degree = null;
        switch (option) {
            case "1" -> {
                degree = "front-end";
                break;
            }
            case "2" -> {
                degree = "back-end";
                break;
            }
            case "3" -> {
                degree = "full-stack";
                break;
            }
            default -> System.out.println("\n" + "Invalid! Please choose an option in the below menu. ");
        }
        return degree;
    }

}
