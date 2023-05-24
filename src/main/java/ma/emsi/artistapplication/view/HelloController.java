package ma.emsi.artistapplication.view;

import javafx.scene.control.RadioButton;
import ma.emsi.artistapplication.entities.Artiste;
import ma.emsi.artistapplication.service.ArtisteService;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import ma.emsi.artistapplication.xssf.SpreadsheetRead;
import ma.emsi.artistapplication.xssf.SpreadsheetWrite;

import java.time.LocalDate;

public class HelloController {
    @FXML
    private TextField nomTextField;
    @FXML
    private TextField prenomTextField;
    @FXML
    private DatePicker dateNaissancePicker;
    @FXML
    private TextField pseudoNomTextField;
    @FXML
    private RadioButton estMortRadioButton;
    @FXML
    private TextField paysOrigineTextField;
    @FXML
    private TextField adresseTextField;
    @FXML
    private Label welcomeText;

    private ArtisteService artisteService;

    public HelloController() {
        artisteService = new ArtisteService();
    }
    @FXML
    protected void onHelloButtonClick() {
        String nom = nomTextField.getText();
        String prenom = prenomTextField.getText();
        LocalDate dateNaissance = dateNaissancePicker.getValue();
        String pseudoNom = pseudoNomTextField.getText();
        boolean estMort = estMortRadioButton.isSelected();
        String paysOrigine = paysOrigineTextField.getText();
        String adresse = adresseTextField.getText();

        java.util.Date dateNaissanceFormatted = java.sql.Date.valueOf(dateNaissance);

        Artiste artiste = new Artiste(null, nom, prenom, dateNaissanceFormatted, pseudoNom, estMort, paysOrigine, adresse);

        // Save the Equipe to the database
        artisteService.save(artiste);

        // Show a success message
        welcomeText.setText("Artiste ajouter avec succée!");
        // Clear the fields
        clearFields();
    }
    @FXML
    private void onAnnulerButtonClick() {
        // Clear the fields
        clearFields();
    }

    private void clearFields() {
        nomTextField.clear();
        prenomTextField.clear();
        dateNaissancePicker.setValue(null);
        pseudoNomTextField.clear();
        estMortRadioButton.setSelected(false);
        paysOrigineTextField.clear();
        adresseTextField.clear();
    }
    @FXML
    private void onClickExportData() throws Exception {
        SpreadsheetWrite.export();
    }
    @FXML
    private void onClickEImporterData() throws Exception {
        SpreadsheetRead.importer();
    }
}