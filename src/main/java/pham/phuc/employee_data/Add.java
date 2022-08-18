package pham.phuc.employee_data;

import employee.pojo.Engineer;
import employee.pojo.Person;
import employee.pojo.Worker;
import employee.service.BaseEmployeeServiceImpl;
import employee.service.engineer.EngineerServiceImpl;
import employee.service.worker.WorkerServiceImpl;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Add extends BaseEmployeeServiceImpl {
    @FXML
    private TextField ID;
    @FXML
    private TextField NAME;
    @FXML
    private TextField AGE;
    @FXML
    private TextField ADDRESS;
    @FXML
    private Label INFO;
    @FXML
    private ComboBox<String> comboBoxLevelDegree;
    @FXML
    private ComboBox<String> comboBoxType;
    @FXML
    private TableView<Person> table;
    @FXML
    private TableColumn idColumn;
    @FXML
    private TableColumn nameColumn;
    @FXML
    private TableColumn ageColumn;
    @FXML
    private TableColumn addressColumn;
    @FXML
    private TableColumn typeColumn;
    @FXML
    private TableColumn lv_degreeColumn;
    private String DATA;

    ObservableList<String> typeList = FXCollections.observableArrayList("Worker", "Engineer");
    ObservableList<String> levelList = FXCollections.observableArrayList("Assistant", "Manager");
    ObservableList<String> degreeList = FXCollections.observableArrayList("Back-end", "Front-end", "Full-stack");

    public void getInfo() {
        String id, name, age, address, level_degree, type;
        id = checkInfo(ID.getText().trim());
        name = checkInfo(NAME.getText().trim());
        age = checkInfo(AGE.getText().trim());
        address = checkInfo(ADDRESS.getText().trim());
        type = comboBoxType.getValue();
        level_degree = comboBoxLevelDegree.getValue();
        DATA = id + "@" + name + "@" + age + "@" + address + "@" + type + "@" + level_degree;
    }

    public String checkInfo(String info) {
        String temp = "";
        if (info.equals(temp)) {
            info = "null";
        }
        return info;
    }

    public void onConfirmButtonClick() {
        getInfo();
        if (checkEmptyText().equals("true")) {
            String[] arrayData = DATA.split("@");
            String confirmInfo = arrayData[0] + " " + arrayData[1] + " " + arrayData[2] + " " + arrayData[3] + " " + arrayData[4] + " " + arrayData[5];
            //tableAddView();
            INFO.setText(confirmInfo);
        } else {
            Alert emptyText = new Alert(Alert.AlertType.ERROR, "Empty Information Error", ButtonType.OK);
            emptyText.initModality(Modality.APPLICATION_MODAL);
            emptyText.showAndWait();
            if (emptyText.getResult() == ButtonType.OK) {
                emptyText.close();
            }
        }
    }

    public String checkEmptyText() {
        String isEmpty = "true";
        String[] checkEmptyText = DATA.split("@");
        for (String s : checkEmptyText) {
            if (s.equals("null")) {
                isEmpty = "false";
                return isEmpty;
            }
        }
        return isEmpty;
    }

    public void onAddInfoButtonClick(ActionEvent event) throws IOException {
        if (DATA == null) {
            Alert emptyText = new Alert(Alert.AlertType.ERROR, "Missing Steps", ButtonType.OK);
            emptyText.setContentText("Please press confirm button to see the final output!");
            emptyText.initModality(Modality.APPLICATION_MODAL);
            emptyText.showAndWait();
            if (emptyText.getResult() == ButtonType.OK) {
                emptyText.close();
            }
        } else {
            Alert addSuccess = new Alert(Alert.AlertType.NONE, "Notification", ButtonType.OK, ButtonType.CANCEL);
            Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            addSuccess.setContentText("Add Employee's Information successfully!");
            addSuccess.initModality(Modality.APPLICATION_MODAL);
            addSuccess.initOwner(stage1);
            addSuccess.showAndWait();

            if (addSuccess.getResult() == ButtonType.CANCEL) {
                Stage stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(Controller.class.getResource("add-view.fxml"));
                Parent addParent = loader.load();
                Scene addScene = new Scene(addParent);
                stage2.setScene(addScene);
            } else {
                addInfo();
                Stage stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(Controller.class.getResource("add-view.fxml"));
                Parent addParent = loader.load();
                Scene addScene = new Scene(addParent);
                stage2.setScene(addScene);
            }
        }
    }

    public void addInfo() {
        String[] arrayData = DATA.split("@");
        if (arrayData[4].equals("Worker")) {
            WorkerServiceImpl worker = new WorkerServiceImpl();
            Worker newWorker = new Worker(Integer.parseInt(arrayData[0]), arrayData[1], Integer.parseInt(arrayData[2]), arrayData[3], arrayData[4], arrayData[5]);
            worker.addToF(newWorker);
        } else {
            EngineerServiceImpl engineer = new EngineerServiceImpl();
            Engineer newEngineer = new Engineer(Integer.parseInt(arrayData[0]), arrayData[1], Integer.parseInt(arrayData[2]), arrayData[3], arrayData[4], arrayData[5]);
            engineer.addToF(newEngineer);
        }
    }

    public void tableAddView() {
        String[] arrayData = DATA.split("@");
        ObservableList<Person> addList = FXCollections.observableArrayList(new Person(Integer.parseInt(arrayData[0]), arrayData[1], Integer.parseInt(arrayData[2]), arrayData[3], arrayData[4], arrayData[5]));
        idColumn.setCellValueFactory(new PropertyValueFactory<Person, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<Person, Integer>("age"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("address"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("type"));
        lv_degreeColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("level"));
        table.setItems(addList);
    }

    public void idSuggestion() throws IOException {
        int suggestID = suggestId();
        ID.setText(String.valueOf(suggestID));
    }

    public void checkIDTextField(MouseEvent event) throws IOException {
        String id = ID.getText();
        int result = checkId(id);
        if (result == -1) {
            Alert duplicateIDError = new Alert(Alert.AlertType.ERROR, "Duplicated ID Error", ButtonType.OK);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            duplicateIDError.setContentText("This ID " + id + " is already had. Please try another id.");
            duplicateIDError.initModality(Modality.APPLICATION_MODAL);
            duplicateIDError.initOwner(stage);
            duplicateIDError.showAndWait();
            if (duplicateIDError.getResult() == ButtonType.OK) {
                ID.clear();
                ID.setPromptText("Please input your ID");
            }
        }
    }

    public void onExitButtonCLick(ActionEvent event) {
        Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION, "Confirmation", ButtonType.OK, ButtonType.CANCEL);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        exitAlert.setContentText("Are you sure you want to exit?");
        exitAlert.initModality(Modality.APPLICATION_MODAL);
        exitAlert.initOwner(stage);
        exitAlert.showAndWait();

        if (exitAlert.getResult() == ButtonType.OK) {
            Platform.exit();
        } else {
            exitAlert.close();
        }
    }

    public void onReturnButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(Controller.class.getResource("hello-view.fxml"));
        Parent addParent = loader.load();
        Scene addScene = new Scene(addParent);
        stage.setScene(addScene);
    }

    public void onMenuButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(Controller.class.getResource("hello-view.fxml"));
        Parent addParent = loader.load();
        Scene addScene = new Scene(addParent);
        stage.setScene(addScene);
    }

    public void onCommentButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(Controller.class.getResource("note-view.fxml"));
        Parent addParent = loader.load();
        Scene addScene = new Scene(addParent);
        stage.setScene(addScene);
    }

    /**
     * ComboBox (Java)
     */
    public void comboBoxType(MouseEvent event) throws IOException {
        comboBoxType.setItems(typeList);
        String id = ID.getText();
        int result = checkId(id);
        if (result == -1) {
            Alert duplicateIDError = new Alert(Alert.AlertType.ERROR, "Duplicated ID Error", ButtonType.OK);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            duplicateIDError.setContentText("This ID " + id + " is already had. Please try another id.");
            duplicateIDError.initModality(Modality.APPLICATION_MODAL);
            duplicateIDError.initOwner(stage);
            duplicateIDError.showAndWait();
            if (duplicateIDError.getResult() == ButtonType.OK) {
                ID.clear();
                ID.setPromptText("Please input your ID");
            }
        }
    }

    public void comboBoxLevelDegree(MouseEvent event) throws IOException {
        String temp = comboBoxType.getValue().trim();
        if (temp.equals("Worker")) {
            comboBoxLevelDegree.setItems(levelList);
        } else if (temp.equals("Engineer")) {
            comboBoxLevelDegree.setItems(degreeList);
        }
        String id = ID.getText();
        int result = checkId(id);
        if (result == -1) {
            Alert duplicateIDError = new Alert(Alert.AlertType.ERROR, "Duplicated ID Error", ButtonType.OK);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            duplicateIDError.setContentText("This ID " + id + " is already had. Please try another id.");
            duplicateIDError.initModality(Modality.APPLICATION_MODAL);
            duplicateIDError.initOwner(stage);
            duplicateIDError.showAndWait();
            if (duplicateIDError.getResult() == ButtonType.OK) {
                ID.clear();
                ID.setPromptText("Please input your ID");
            }
        }
    }

    @Override
    public void add() throws IOException {

    }

    @Override
    public void show() throws IOException {

    }
}
