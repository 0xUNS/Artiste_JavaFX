package ma.emsi.artistapplication;

import ma.emsi.artistapplication.entities.Artiste;
import ma.emsi.artistapplication.service.ArtisteService;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.time.LocalDate;
import java.util.Date;


//public class HelloController {
//    @FXML
//    private Label welcomeText;
//
//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }
//}

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
    private TextField estMortTextField;
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
        boolean estMort = Boolean.parseBoolean(estMortTextField.getText());
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
        estMortTextField.clear();
        paysOrigineTextField.clear();
        adresseTextField.clear();
    }
}