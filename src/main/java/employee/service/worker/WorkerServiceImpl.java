package employee.service.worker;

import employee.pojo.Person;
import employee.pojo.Worker;
import employee.pojo.utils.EmployeeConstants;
import employee.service.BaseEmployeeServiceImpl;

import java.io.*;
import java.util.*;

public class WorkerServiceImpl extends BaseEmployeeServiceImpl implements WorkerService<Worker> {
    Scanner sc = new Scanner(System.in);

    @Override
    public void add() throws IOException {
        System.out.println();
        int id = inputId();
        String name = inputName();
        int age = inputAge();
        String address = inputAddress();
        String level = chooseLevel();
        System.out.println();
        String type = "worker";
        List<Person> employee = new ArrayList<>();
        employee.add(new Worker(id, name, age, address, type, level));
        Worker worker = new Worker(id, name, age, address, type, level);
        Worker woToObject = new Worker(id, name, age, address, type, level);
        addToF(worker);
        addToObject(woToObject);
    }

    @Override
    public void show() throws IOException {
        System.out.println(" ");
        File file = new File(EmployeeConstants.LIST_PATH);
        if (file.length() == 0) {
            System.out.println("No data in the list. Please insert a new employee in the list.");
        } else {
            System.out.println("-------------------------------------------------------------------------------------------------");
            Formatter fmt = new Formatter();
            fmt.format("%15s %15s %15s %15s %15s %15s\n", "EMPLOYEE ID", "NAME", "AGE", "ADDRESS", "TYPE", "LEVEL/DEGREE");
            BufferedReader reader = new BufferedReader(new FileReader(EmployeeConstants.LIST_PATH));
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] array = currentLine.split("@");
                if (array[4].equals("worker")) {
                    fmt.format("%10s %20s %15s %15s %15s %14s\n", array[0], array[1], array[2], array[3], array[4], array[5]);
                }
            }
            System.out.print(fmt);
            System.out.println("-------------------------------------------------------------------------------------------------");
            System.out.println("");
            reader.close();
        }
    }


    private boolean checkFileExist() {
        File file = new File(EmployeeConstants.LIST_PATH);
        return file.exists();
    }

    private void addToF(Worker worker) {
        FileWriter myList = null;
        try {
            if (checkFileExist()) {
                myList = new FileWriter(EmployeeConstants.LIST_PATH, true);
                myList.write(worker.toString() + "\n");
            } else {
                myList = new FileWriter(EmployeeConstants.LIST_PATH);
                myList.write(worker.toString() + "\n");
            }
            myList.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    private void addToObject(Worker woToObject) {
        try (FileOutputStream fos = new FileOutputStream("object.list"); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(woToObject.toString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int inputId() throws IOException {
        while (true) {
            int suggestId = suggestId();
            System.out.println("Suggest ID: " + suggestId);
            System.out.print(EmployeeConstants.INPUT_ID);
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
        File check = new File(EmployeeConstants.LIST_PATH);
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
        File file = new File(EmployeeConstants.LIST_PATH);
        String currentLine;
        if (file.length() == 0) {
            num = 1;
        } else {
            ArrayList<Integer> ar = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(EmployeeConstants.LIST_PATH));
            while ((currentLine = reader.readLine()) != null) {
                String[] array = currentLine.split("@");
                ar.add(Integer.parseInt(array[0]));
            }
            reader.close();
            int length = Collections.max(ar);
            if (length == ar.size()) {
                num = length + 1;
            } else {
                for (int i = 1; i <= length; i++) {
                    if (ar.contains(i)) {
                        continue;
                    } else {
                        num = i;
                        return num;
                    }
                }
            }
        }
        return num;
    }

    public String inputName() {
        System.out.print(EmployeeConstants.INPUT_NAME);
        return sc.nextLine();
    }

    public int inputAge() {
        System.out.print(EmployeeConstants.INPUT_AGE);
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
        System.out.print(EmployeeConstants.INPUT_ADDRESS);
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
            case "1" -> level = "employee";
            case "2" -> level = "manager";
            default -> System.out.println("\n" + "Invalid! Please choose an option in the below menu. ");
        }
        return level;
    }
}
