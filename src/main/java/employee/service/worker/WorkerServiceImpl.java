package employee.service.worker;

import employee.Manage;
import employee.pojo.Worker;
import employee.service.BaseEmployeeServiceImpl;

import java.io.*;
import java.util.Scanner;

public class WorkerServiceImpl extends BaseEmployeeServiceImpl implements WorkerService<Worker> {
    Scanner sc = new Scanner(System.in);

    @Override
    public void add() {
        System.out.println(" ");
        int id = inputId();
        String name = inputName();
        int age = inputAge();
        String address = inputAddress();
        System.out.println(" ");
        String type = "worker";
        Worker worker = new Worker(id, name, age, address, type);
        addToF(worker);
    }

    @Override
    public void showLevel(Worker worker) {

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

}
