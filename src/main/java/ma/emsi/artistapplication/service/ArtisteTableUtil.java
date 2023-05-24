package ma.emsi.artistapplication.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.emsi.artistapplication.entities.Artiste;

import java.util.List;
import java.util.Date;

public class ArtisteTableUtil {
	private static ArtisteService artisteService = new ArtisteService();
	public static ObservableList<Artiste> getArtisteList() {
		List<Artiste> artistes = artisteService.findAll();
		return FXCollections.<Artiste>observableArrayList(artistes);
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
		pseudoNomCol.setCellValueFactory(new PropertyValueFactory<>("pseudoNom"));
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
