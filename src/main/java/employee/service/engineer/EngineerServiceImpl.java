package employee.service.engineer;

import employee.Manage;
import employee.pojo.Engineer;
import employee.pojo.Person;
import employee.pojo.utils.EmployeeConstants;
import employee.service.BaseEmployeeServiceImpl;

import java.io.*;
import java.util.*;

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
        employee.add(new Engineer(id, name, age, address, type, degree));
        Engineer en = new Engineer(id, name, age, address, type, degree);
        Engineer enToObject = new Engineer(id, name, age, address, type, degree);
        addToF(en);
        addToObject(enToObject);
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
                if (array[4].equals("engineer")) {
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

    private void addToF(Engineer en) {
        FileWriter myList = null;
        try {
            if (checkFileExist()) {
                myList = new FileWriter(EmployeeConstants.LIST_PATH, true);
                myList.write(en.toString() + "\n");
            } else {
                myList = new FileWriter(EmployeeConstants.LIST_PATH);
                myList.write(en.toString() + "\n");
            }
            myList.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addToObject(Engineer enToObject) {
        try {
            FileOutputStream fos = new FileOutputStream("object.list");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // Write object to file
            oos.writeObject(enToObject.toString() + "\n");
            // Close resources
            fos.close();
            oos.close();
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
            default -> System.out.println("\n" + EmployeeConstants.INVALID_OUTPUT);
        }
        return degree;
    }

}
