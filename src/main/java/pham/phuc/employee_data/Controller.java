package pham.phuc.employee_data;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

public class Controller {
    @FXML
    private Label welcomeText0;
    @FXML
    private ComboBox<String> comboBoxType;
    @FXML
    private ComboBox<String> comboBoxLevelDegree;

    ObservableList<String> typeList = FXCollections.observableArrayList("Worker", "Engineer");
    ObservableList<String> lvdegreeList = FXCollections.observableArrayList("Assistant", "Manager", "Back-end", "Front-end", "Full-stack");
    @FXML

    protected void onHelloButtonClick() {
        welcomeText0.setText("Welcome to JavaFX Application!");
    }

    @FXML
    public void onAddButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(Controller.class.getResource("add-view.fxml"));
        Parent addParent = loader.load();
        Scene addScene = new Scene(addParent);
        stage.setScene(addScene);
    }

    @FXML
    public void onExitButtonCLick() {
        Platform.exit();
    }

    @FXML
    public void onAddReturnClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(Controller.class.getResource("hello-view.fxml"));
        Parent addParent = loader.load();
        Scene addScene = new Scene(addParent);
        stage.setScene(addScene);
    }

    /**
     * ComboBox (Java)
     */
    @FXML
    public void comboBoxType() {
        comboBoxType.setItems(typeList);
    }

    @FXML
    public void comboBoxLevelDegree() {
        comboBoxLevelDegree.setItems(lvdegreeList);
    }
}