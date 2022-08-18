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
    private TableColumn<Person, Integer> idColumn;
    @FXML
    private TableColumn<Person, String> nameColumn;
    @FXML
    private TableColumn<Person, Integer> ageColumn;
    @FXML
    private TableColumn<Person, String> addressColumn;
    @FXML
    private TableColumn<Person, String> typeColumn;
    @FXML
    private TableColumn<Person, String> lv_degreeColumn;
    private String DATA;

    ObservableList<String> typeList = FXCollections.observableArrayList("Worker", "Engineer");
    ObservableList<String> levelList = FXCollections.observableArrayList("Assistant", "Manager");
    ObservableList<String> degreeList = FXCollections.observableArrayList("Back-end", "Front-end", "Full-stack");
    ObservableList<Person> addList;

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

    public void onConfirmButtonClick() {
        getInfo();
        String[] arrayData = DATA.split("@");
        String confirmInfo = arrayData[0] + " " + arrayData[1] + " " + arrayData[2] + " " + arrayData[3] + " " + arrayData[4] + " " + arrayData[5];
        //tableAddView();
        INFO.setText(confirmInfo);
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

    public void onAddInfoButtonClick(ActionEvent event) throws IOException {
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

    public void tableAddView() {
        String[] arrayData = DATA.split("@");
        if (arrayData[4].equals("Worker")) {
            addList = FXCollections.observableArrayList(new Worker(Integer.parseInt(arrayData[0]), arrayData[1], Integer.parseInt(arrayData[2]), arrayData[3], arrayData[4], arrayData[5]));
        } else {
            addList = FXCollections.observableArrayList(new Engineer(Integer.parseInt(arrayData[0]), arrayData[1], Integer.parseInt(arrayData[2]), arrayData[3], arrayData[4], arrayData[5]));
        }
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        lv_degreeColumn.setCellValueFactory(new PropertyValueFactory<>("level"));
        table.setItems(addList);
    }

    public String checkInfo(String info) {
        String temp = "";
        if (info.equals(temp)) {
            info = "null";
        }
        return info;
    }

    public void idSuggestion() throws IOException {
        int suggestID = suggestId();
        ID.setText(String.valueOf(suggestID));
    }

    public void checkIDTextField(MouseEvent event) throws IOException {
        String id = ID.getText();
        int result = checkId(id);
        if (result == - 1) {
            Alert duplicateIDError = new Alert(Alert.AlertType.ERROR, "Duplicated ID Error" ,ButtonType.OK);
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
        if (result == - 1) {
            Alert duplicateIDError = new Alert(Alert.AlertType.ERROR, "Duplicated ID Error" ,ButtonType.OK);
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
        if (result == - 1) {
            Alert duplicateIDError = new Alert(Alert.AlertType.ERROR, "Duplicated ID Error" ,ButtonType.OK);
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
