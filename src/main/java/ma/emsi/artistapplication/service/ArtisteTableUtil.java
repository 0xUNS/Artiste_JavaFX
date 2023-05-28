package ma.emsi.artistapplication.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.emsi.artistapplication.entities.Artiste;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;

public class ArtisteTableUtil {
	private static final ArtisteService artisteService = new ArtisteService();
	public static ObservableList<Artiste> getArtisteList() {
		List<Artiste> artistes = artisteService.findAll();
		return FXCollections.<Artiste>observableArrayList(artistes);
	}

	public static TableColumn<Artiste, Void> getEditColumn(Consumer<Artiste> editAction) {
		TableColumn<Artiste, Void> column = new TableColumn<>("Edit");
		column.setCellFactory(param -> new TableCell<>() {
			private final Button editButton = new Button("Edit");

			{
				editButton.setOnAction(event -> {
					Artiste artiste = getTableRow().getItem();
					if (artiste != null) {
						editAction.accept(artiste);
					}
				});
			}

			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setGraphic(null);
				} else {
					setGraphic(editButton);
				}
			}
		});
		return column;
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
	public static TableColumn<Artiste, LocalDate> getDateNaissenceColumn() {
		TableColumn<Artiste, LocalDate> dateNaissanceCol = new TableColumn<>("Date Naissance");
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
