package ma.emsi.artistapplication.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import ma.emsi.artistapplication.entities.Artiste;
import ma.emsi.artistapplication.service.ArtisteService;
import ma.emsi.artistapplication.service.ArtisteTableUtil;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

import javafx.scene.layout.HBox;
import static javafx.scene.control.TableView.TableViewSelectionModel;

public class TableViewAddDeleteRows extends Application {
	private static ArtisteService artisteService = new ArtisteService();
	// Fields to add Artiste details
	private final TextField nomTextField = new TextField();
	private final TextField prenomTextField = new TextField();
	private final DatePicker dateNaissancePicker = new DatePicker();
	private final TextField pseudoNomTextField = new TextField();
	private final RadioButton estMortRadioButton = new RadioButton();
	private final TextField paysOrigineTextField = new TextField();
	private final TextField adresseTextField = new TextField();
	private final TextField pathTextField = new TextField();


	// The TableView
	TableView<Artiste> table = new TableView<>(ArtisteTableUtil.getArtisteList());

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void start(Stage stage) {
		// Turn on multi-row selection for the TableView
		TableViewSelectionModel<Artiste> tsm = table.getSelectionModel();
		tsm.setSelectionMode(SelectionMode.MULTIPLE);

		// Add columns to the TableView
		table.getColumns().addAll(ArtisteTableUtil.getIdColumn(),
				ArtisteTableUtil.getNomColumn(),
				ArtisteTableUtil.getPrenomColumn(),
				ArtisteTableUtil.getDateNaissenceColumn(),
				ArtisteTableUtil.getPseudoNomColumn(),
				ArtisteTableUtil.getEstMortColumn(),
				ArtisteTableUtil.getPaysOrigineColumn(),
				ArtisteTableUtil.getAdresseColumn());

		GridPane formDataPane = this.getNewArtisteDataPane();

		Button restoreBtn = new Button("Restore Rows");
		restoreBtn.setOnAction(e -> restoreRows());

		Button deleteBtn = new Button("Delete Selected Rows");
		deleteBtn.setOnAction(e -> deleteSelectedRows());

		GridPane expImpDataPane = this.getExpImpDataPane();

		VBox root = new VBox(formDataPane, new HBox(restoreBtn, deleteBtn), expImpDataPane, table);
		root.setSpacing(5);
		root.setStyle("-fx-padding: 10;" +
				"-fx-border-style: solid inside;" +
				"-fx-border-width: 2;" +
				"-fx-border-insets: 5;" +
				"-fx-border-radius: 5;" +
				"-fx-border-color: #999;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Adding/Deleting Rows in a TableViews");
		stage.show();
	}

	public GridPane getNewArtisteDataPane() {
		GridPane pane = new GridPane();
		pane.setHgap(10);
		pane.setVgap(5);
		pane.addRow(0, new Label("First Name:"), nomTextField);
		pane.addRow(1, new Label("Last Name:"), prenomTextField);
		pane.addRow(2, new Label("Birth Date:"), dateNaissancePicker);
		pane.addRow(3, new Label("pseudo Nom:"), pseudoNomTextField);
		pane.addRow(4, new Label("est Mort:"), estMortRadioButton);
		pane.addRow(5, new Label("Pays Origine:"), paysOrigineTextField);
		pane.addRow(6, new Label("Adresse:"), adresseTextField);

		Button addBtn = new Button("Add");
		addBtn.setOnAction(e -> addArtiste());

		// Add the "Add" button
		pane.add(addBtn, 2, 0);

		return pane;
	}

	public GridPane getExpImpDataPane() {
		GridPane pane = new GridPane();
		pane.setHgap(10);
		pane.setVgap(5);
		pane.addRow(0, new Label("Path:"), pathTextField);
		Button exportBtn = new Button("Export");
		exportBtn.setOnAction(e -> {
			try {
				artisteService.exporterVersExcel(pathTextField.getText());
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		});
		Button importBtn = new Button("Import");
		importBtn.setOnAction(e -> {
			try {
				artisteService.importerDepuisExcel(pathTextField.getText());
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		});

		pane.add(exportBtn, 2, 0);
		pane.add(importBtn, 3, 0);
		return pane;
	}

	public void deleteSelectedRows() {
		TableViewSelectionModel<Artiste> tsm = table.getSelectionModel();
		if (tsm.isEmpty()) {
			System.out.println("Please select a row to delete.");
			return;
		}

		// Get all selected row indices in an array
		ObservableList<Integer> list = tsm.getSelectedIndices();
		Integer[] selectedIndices = new Integer[list.size()];
		selectedIndices = list.toArray(selectedIndices);

		// Sort the array
		Arrays.sort(selectedIndices);

		// Delete rows (last to first)
		for(int i = selectedIndices.length - 1; i >= 0; i--) {
			tsm.clearSelection(selectedIndices[i].intValue());
			artisteService.remove(table.getItems().get(selectedIndices[i].intValue()));
			restoreRows();
		}
	}

	public void restoreRows() {
		table.getItems().clear();
		table.getItems().addAll(ArtisteTableUtil.getArtisteList());
	}



	public void addArtiste() {
		String nom = nomTextField.getText();
		String prenom = prenomTextField.getText();
		LocalDate dateNaissance = dateNaissancePicker.getValue();
		String pseudoNom = pseudoNomTextField.getText();
		boolean estMort = estMortRadioButton.isSelected();
		String paysOrigine = paysOrigineTextField.getText();
		String adresse = adresseTextField.getText();
		java.util.Date dateNaissanceFormatted = java.sql.Date.valueOf(dateNaissance);

		Artiste artiste = new Artiste(null, nom, prenom, dateNaissanceFormatted, pseudoNom, estMort, paysOrigine, adresse);

		artisteService.save(artiste);
		restoreRows();
		clearFields();
	}

	public void clearFields() {
		nomTextField.clear();
		prenomTextField.clear();
		dateNaissancePicker.setValue(null);
		pseudoNomTextField.clear();
		estMortRadioButton.setSelected(false);
		paysOrigineTextField.clear();
		adresseTextField.clear();
		pathTextField.setText("Documents");
	}
}
