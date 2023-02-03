package resistronv1000.projectresistron;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * C'est le controleur de l'application javaFX, elle va contenir
 * tous les méthodes appelées en fonction des événements ce passant dans l'interface utilisateur
 */
public class ResistronController {
    @FXML
    private ComboBox<String> ring1ComboBox;
    @FXML
    private ComboBox<String> ring2ComboBox;
    @FXML
    private ComboBox<String> ringMultComboBox;
    @FXML
    private ComboBox<String> ringTolerComboBox;
    @FXML
    private ComboBox<String> multipleOfResult;
    @FXML
    private TextField resultTextField;
    @FXML
    private Label resultLabel;
    @FXML
    private Label resultToleranceLabel;

    private String[] colorsRing1 = {"Noir", "Marron", "Rouge", "Orange", "Jaune", "Vert", "Bleu", "Violet", "Gris", "Blanc"};
    private String[] colorsRing2 = {"Noir", "Marron", "Rouge", "Orange", "Jaune", "Vert", "Bleu", "Violet", "Gris", "Blanc", "Or", "Argent"};
    private String[] colorsRingMult = {"Marron", "Rouge", "Vert", "Bleu", "Violet", "Gris", "Or", "Argent"};
    private double[] multipliers = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000,100000000,1000000000, 0.1, 0.01};
    private String[] tolerance = {"± 1", "± 2", "± 0.5", "± 0.25", "± 0.10", "± 0.05","± 5", "± 10"};
    private String[] units = {"Ω", "kΩ", "MΩ", "GΩ"};
    private double[] multiplUnits = {1, 1000, 1000000, 1000000000};

    /**
     * La méthode "initialize" est appelée automatiquement lorsque la scène associée à ce contrôleur est initialisée.
     *
     *
     * Cette méthode initialise les listes déroulantes de l'interface utilisateur avec des valeurs prédéfinies.
     *
     *
     *  Elle établit également des "listeners" pour chaque liste déroulante, qui sont des méthodes qui sont appelées
     *  lorsque la sélection dans la liste déroulante change.
     *
     * Les listeners pour les listes déroulantes déclenchent la méthode "updateResult".
     */
    @FXML
    private void initialize(){
        /*
        Condition that adds all elements of a list on comboBox if it is empty
         */
        if (ring1ComboBox.getItems().isEmpty()) {
            ring1ComboBox.getItems().addAll(colorsRing1);
        }
        if (ring2ComboBox.getItems().isEmpty()) {
            ring2ComboBox.getItems().addAll(colorsRing1);
        }
        if (ringMultComboBox.getItems().isEmpty()) {
            ringMultComboBox.getItems().addAll(colorsRing2);
        }
        if (ringTolerComboBox.getItems().isEmpty()) {
            ringTolerComboBox.getItems().addAll(colorsRingMult);
        }

        if (multipleOfResult.getItems().isEmpty()) {
            multipleOfResult.getItems().addAll(units);
        }
        /*
         adds a listeners for all selectedIndexProperty, for all objects of comboBox, and update result whenever
         the selected index changes, and call method updateResult
         */
        ringTolerComboBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> updateResult());
        ring1ComboBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> updateResult());
        ring2ComboBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> updateResult());
        ringMultComboBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> updateResult());
        multipleOfResult.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> updateResult());

        /*
         Retrieves the selected index, checks if it is a valid index.
            For integrity, if index not selected still set a thing.
         */
        int color4Index = ringTolerComboBox.getSelectionModel().getSelectedIndex();
        if (color4Index >= 0) {
            resultToleranceLabel.setText(tolerance[color4Index]);
        } else {
            resultToleranceLabel.setText("± 0");
        }
    }

    /**
     * La méthode "updateResult" est appelée lorsque la sélection dans l'une des listes déroulantes change.
     *
     * Elle calcule la résistance d'après les couleurs sélectionnées sur les anneaux du composant de résistance et met à jour l'affichage en conséquence.
     *
     * Si aucune sélection n'a été faite dans l'une des listes déroulantes, la méthode ne fait rien.
     *
     *
     * Elle calcule la résistance en utilisant les indices(i) de sélection des couleurs dans les listes déroulantes et en utilisant le tableau "multipliers".
     *
     *  Il calcule également la résistance en unités en utilisant le tableau "multiplUnits".
     *
     *  Le résultat final est affiché dans les étiquettes "result1" et "resultTolerance".
     */
    @FXML
    private void updateResult() {

        /*
        This code checks if at least one of the comboBox's selection is empty,
        Allows to not execute the code if all comboBox as not selected.
         */
        if (ring1ComboBox.getSelectionModel().isEmpty() || ring2ComboBox.getSelectionModel().isEmpty()
                || ringMultComboBox.getSelectionModel().isEmpty() || ringTolerComboBox.getSelectionModel().isEmpty()
                || multipleOfResult.getSelectionModel().isEmpty()) {
            return;
        }
        /*
        Allows to have the variable usable in the calculation, compared to index of comboBox
         */
        int ring1Index = ring1ComboBox.getSelectionModel().getSelectedIndex();
        int ring2Index = ring2ComboBox.getSelectionModel().getSelectedIndex();
        int ringMultiplIndex = ringMultComboBox.getSelectionModel().getSelectedIndex();
        int ringTolerIndex = ringTolerComboBox.getSelectionModel().getSelectedIndex();
        int multiplIndex = multipleOfResult.getSelectionModel().getSelectedIndex();

        /*
        Calculation that determines the result
         */
        double resistance = ((ring1Index * 10 + ring2Index) * multipliers[ringMultiplIndex]);

        /*
        Calculation that determines the multiple in ohm
         */
        double resistanceUnits = ((ring1Index * 10 + ring2Index) * multipliers[ringMultiplIndex]) / multiplUnits[multiplIndex];

        /*
        Condition that add a 0 after the decimal point
         */
        if (multiplIndex == 3) {
            resultLabel.setText(String.format("%.5f", resistanceUnits));
        }
        else if (multiplIndex == 2) {
            resultLabel.setText(String.format("%.4f", resistanceUnits));
        }
        else if (multiplIndex == 1) {
            resultLabel.setText(String.format("%.3f", resistanceUnits));
        }
        else {
            resultLabel.setText(String.format("%.2f", resistanceUnits));
        }

            resultTextField.setText(String.format("%.2f Ω", resistance ));

        /*
        Add tolerance to resultTolerance in terms of index of color4
         */
        resultToleranceLabel.setText(tolerance[ringTolerIndex]);

    }

}



