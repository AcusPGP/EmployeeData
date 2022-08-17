package pham.phuc.employee_data;

import employee.service.BaseEmployeeServiceImpl;
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

    ObservableList<String> typeList = FXCollections.observableArrayList("Worker", "Engineer");
    ObservableList<String> levelList = FXCollections.observableArrayList("Assistant", "Manager");
    ObservableList<String> degreeList = FXCollections.observableArrayList("Back-end", "Front-end", "Full-stack");

    public String getInfo() {
        String id, name, age, address, level_degree, type;
        id = checkInfo(ID.getText().trim());
        name = checkInfo(NAME.getText().trim());
        age = checkInfo(AGE.getText().trim());
        address = checkInfo(ADDRESS.getText().trim());
        type = comboBoxType.getValue().trim();
        level_degree = comboBoxLevelDegree.getValue().trim();
        String info = id + " " + name + " " + age + " " + address + " " + type + " " + level_degree;
        INFO.setText(info);
        return info;
    }

    public String checkInfo(String info) {
        String temp = "";
        if (info.equals(temp)) {
            info = "null";
            return info;
        } else {
            return info;
        }
    }

    public void idSuggestion() throws IOException {
        int suggestID = suggestId();
        ID.setText(String.valueOf(suggestID));
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

    public void onReturnClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(Controller.class.getResource("hello-view.fxml"));
        Parent addParent = loader.load();
        Scene addScene = new Scene(addParent);
        stage.setScene(addScene);
    }

    public void onMenuClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(Controller.class.getResource("hello-view.fxml"));
        Parent addParent = loader.load();
        Scene addScene = new Scene(addParent);
        stage.setScene(addScene);
    }

    /**
     * ComboBox (Java)
     */
    public void comboBoxType() {
        comboBoxType.setItems(typeList);
    }

    public void comboBoxLevelDegree() {
        String temp = comboBoxType.getValue().trim();
        if (temp.equals("Worker")) {
            comboBoxLevelDegree.setItems(levelList);
        } else if (temp.equals("Engineer")) {
            comboBoxLevelDegree.setItems(degreeList);
        }
    }

    @Override
    public void add() throws IOException {

    }

    @Override
    public void show() throws IOException {

    }
}
