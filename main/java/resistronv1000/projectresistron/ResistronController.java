package resistronv1000.projectresistron;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ResistronController {
    @FXML
    private ComboBox<String> ring1;
    @FXML
    private ComboBox<String> ring2;
    @FXML
    private ComboBox<String> ringMultipliers;
    @FXML
    private ComboBox<String> ringTolerance;
    @FXML
    private ComboBox<String> multipl;
    @FXML
    private TextField result;
    @FXML
    private Label result1;
    @FXML
    private Label resultTolerance;

    private String[] colors = {"Noir", "Marron", "Rouge", "Orange", "Jaune", "Vert", "Bleu", "Violet", "Gris", "Blanc"};
    private String[] colors1 = {"Noir", "Marron", "Rouge", "Orange", "Jaune", "Vert", "Bleu", "Violet", "Gris", "Blanc", "Or", "Argent"};
    private String[] colors2 = {"Marron", "Rouge", "Vert", "Bleu", "Violet", "Gris", "Or", "Argent"};
    private double[] multipliers = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000,100000000,1000000000, 0.1, 0.01};
    private String[] tolerance = {"± 1", "± 2", "± 0.5", "± 0.25", "± 0.10", "± 0.05","± 5", "± 10"};
    private String[] units = {"Ω", "kΩ", "MΩ", "GΩ"};
    private double[] multiplUnits = {1, 1000, 1000000, 1000000000};





    @FXML
    private void initialize(){

        if (ring1.getItems().isEmpty()) {
            ring1.getItems().addAll(colors);
        }
        if (ring2.getItems().isEmpty()) {
            ring2.getItems().addAll(colors);
        }
        if (ringMultipliers.getItems().isEmpty()) {
            ringMultipliers.getItems().addAll(colors1);
        }
        if (ringTolerance.getItems().isEmpty()) {
            ringTolerance.getItems().addAll(colors2);
        }

        if (multipl.getItems().isEmpty()) {
            multipl.getItems().addAll(units);
        }

        ringTolerance.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> updateResult());
        ring1.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> updateResult());
        ring2.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> updateResult());
        ringMultipliers.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> updateResult());
        multipl.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> updateResult());


        int color4Index = ringTolerance.getSelectionModel().getSelectedIndex();
        if (color4Index >= 0) {
            resultTolerance.setText(tolerance[color4Index]);
        } else {
            resultTolerance.setText("± 0");
        }


    }

    @FXML
    private void updateResult() {

        if (ring1.getSelectionModel().isEmpty() || ring2.getSelectionModel().isEmpty()
                || ringMultipliers.getSelectionModel().isEmpty() || ringTolerance.getSelectionModel().isEmpty()
                || multipl.getSelectionModel().isEmpty()) {
            return;
        }

        int color1Index = ring1.getSelectionModel().getSelectedIndex();
        int color2Index = ring2.getSelectionModel().getSelectedIndex();
        int color3Index = ringMultipliers.getSelectionModel().getSelectedIndex();
        int color4Index = ringTolerance.getSelectionModel().getSelectedIndex();
        int multiplIndex = multipl.getSelectionModel().getSelectedIndex();



        double resistance = ((color1Index * 10 + color2Index) * multipliers[color3Index]);
        double resistanceUnits = ((color1Index * 10 + color2Index) * multipliers[color3Index]) / multiplUnits[multiplIndex];

        if (multiplIndex == 3) {
            result1.setText(String.format("%.5f", resistanceUnits));
        }
        else if (multiplIndex == 2) {
            result1.setText(String.format("%.4f", resistanceUnits));
        }
        else if (multiplIndex == 1) {
            result1.setText(String.format("%.3f", resistanceUnits));
        }
        else {
            result1.setText(String.format("%.2f", resistanceUnits));
        }

            result.setText(String.format("%.2f Ω", resistance ));


        resultTolerance.setText(tolerance[color4Index]);

    }

}



