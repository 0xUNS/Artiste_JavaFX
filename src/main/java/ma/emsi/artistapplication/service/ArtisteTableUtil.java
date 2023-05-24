package ma.emsi.artistapplication.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.emsi.artistapplication.entities.Artiste;

import java.time.LocalDate;
import java.util.Date;

public class ArtisteTableUtil {
	public static ObservableList<Artiste> getArtisteList() {
		Artiste p1 = new Artiste(1, "Ashwin", "Sharan", new Date(), "Ash", false, "France", "Paris, France");
		Artiste p2 = new Artiste(2, "Ashwin", "Sharan", new Date(), "Sha", false, "Germany", "Berlin, Germany");
		Artiste p3 = new Artiste(3, "Ashwin", "Sharan", new Date(), "Ran", false, "UK", "Carddif, UK");
		return FXCollections.<Artiste>observableArrayList(p1, p2, p3);
	}

	/* Returns Id TableColumn */
	public static TableColumn<Artiste, Integer> getIdColumn() {
		TableColumn<Artiste, Integer> IdCol = new TableColumn<>("Id");
		IdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		return IdCol;
	}

	/* Returns First Name TableColumn */
	public static TableColumn<Artiste, String> getNomColumn() {
		TableColumn<Artiste, String> fNameCol = new TableColumn<>("First Name");
		fNameCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
		return fNameCol;
	}

	/* Returns Last Name TableColumn */
	public static TableColumn<Artiste, String> getPrenomColumn() {
		TableColumn<Artiste, String> lastNameCol = new TableColumn<>("Last Name");
		lastNameCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		return lastNameCol;
	}

	/* Returns Birth Date TableColumn */
	public static TableColumn<Artiste, Date> getDateNaissenceColumn() {
		TableColumn<Artiste, Date> dateNaissanceCol = new TableColumn<>("Date Naissance");
		dateNaissanceCol.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
		return dateNaissanceCol;
	}

	public static TableColumn<Artiste, String> getPseudoNomColumn() {
		TableColumn<Artiste, String> pseudoNomCol = new TableColumn<>("Pseudo Nom");
		pseudoNomCol.setCellValueFactory(new PropertyValueFactory<>("pseudonom"));
		return pseudoNomCol;
	}

	public static TableColumn<Artiste, String> getEstMortColumn() {
		TableColumn<Artiste, String> estMortCol = new TableColumn<>("est Mort");
		estMortCol.setCellValueFactory(new PropertyValueFactory<>("estMort"));
		return estMortCol;
	}

	public static TableColumn<Artiste, String> getPaysOrigineColumn() {
		TableColumn<Artiste, String> paysOrigineCol = new TableColumn<>("Pays Origine");
		paysOrigineCol.setCellValueFactory(new PropertyValueFactory<>("paysOrigine"));
		return paysOrigineCol;
	}

	public static TableColumn<Artiste, String> getAdresseColumn() {
		TableColumn<Artiste, String> adresseCol = new TableColumn<>("Adresse");
		adresseCol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
		return adresseCol;
	}
}
