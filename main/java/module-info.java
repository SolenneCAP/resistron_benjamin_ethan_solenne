module resistronv1000.projectresistron {
    requires javafx.controls;
    requires javafx.fxml;


    opens resistronv1000.projectresistron to javafx.fxml;
    exports resistronv1000.projectresistron;
}