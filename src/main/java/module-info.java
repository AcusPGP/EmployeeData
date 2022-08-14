module pham.phuc.employee_data {
    requires javafx.controls;
    requires javafx.fxml;

    opens pham.phuc.employee_data to javafx.fxml;
    exports pham.phuc.employee_data;
}