package employee.service.worker;

import employee.Manage;
import employee.pojo.Person;
import employee.pojo.Worker;
import employee.service.BaseEmployeeServiceImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WorkerServiceImpl extends BaseEmployeeServiceImpl implements WorkerService<Worker> {
    Scanner sc = new Scanner(System.in);

    @Override
    public void add() {
        System.out.println();
        int id = inputId();
        String name = inputName();
        int age = inputAge();
        String address = inputAddress();
        String level = chooseLevel();
        System.out.println();
        String type = "worker";
        List<Person> employee = new ArrayList<>();
        employee.add(new Person(id, name, age, address, type, level));
        Worker worker = new Worker(id, name, age, address, type, level);
        addToF(worker);
    }

    @Override
    public void show() throws IOException {
        System.out.println("\n");
        File file = new File("/Users/macbook/OOPProjects/Employee_Data/list.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            String[] array = currentLine.split("@");
            if (array[4].equals("worker")) {
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

    private void addToF(Worker worker) {
        FileWriter myList = null;
        try {
            if (checkFileExist()) {
                myList = new FileWriter(Manage.FILE_PATH, true);
                myList.write(worker.toString() + "\n");
            } else {
                myList = new FileWriter(Manage.FILE_PATH);
                myList.write(worker.toString() + "\n");
            }
            myList.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public int inputId() {
        while (true) {
            System.out.print("Input the id: ");
            try {
                String id = sc.nextLine().trim();
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

    public String chooseLevel() {
        System.out.println("Choose level for the worker.");
        System.out.println("1. Employee");
        System.out.println("2. Manager");
        System.out.print("Please choose an option: ");
        String option = sc.nextLine().trim();
        String level = null;
        switch (option) {
            case "1" -> {
                level = "employee";
                break;
            }
            case "2" -> {
                level = "manager";
                break;
            }
            default -> System.out.println("\n" + "Invalid! Please choose an option in the below menu. ");
        }
        return level;
    }
}
