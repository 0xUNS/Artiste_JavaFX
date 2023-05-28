package ma.emsi.artistapplication.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ma.emsi.artistapplication.entities.User;

import java.util.ArrayList;
import java.util.List;

public class Login extends Application {
	// Fields to add Artiste details
	private final TextField userNameField = new TextField();
	private final PasswordField passwordField = new PasswordField();

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {

		GridPane formDataPane = this.getLoginDataPane();

		VBox root = new VBox(formDataPane);
		root.setSpacing(5);
		root.setStyle("-fx-padding: 10;" +
				"-fx-border-style: solid inside;" +
				"-fx-border-width: 2;" +
				"-fx-border-insets: 5;" +
				"-fx-border-radius: 5;" +
				"-fx-border-color: #999;");

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Login Page");
		stage.show();
	}

	public GridPane getLoginDataPane() {
		GridPane pane = new GridPane();
		pane.setHgap(20);
		pane.setVgap(20);
		Label label_hint = new Label("admin:admin");
		label_hint.setTextFill(Color.web("red"));
		pane.addRow(0, label_hint);

		pane.addRow(1, new Label("User Name:"), userNameField);
		pane.addRow(2, new Label("Password:"), passwordField);

		Button loginBtn = new Button("Login");
		loginBtn.setOnAction(e -> verifyData());
		Button restoreBtn = new Button("Annuler");
		restoreBtn.setOnAction(e -> clearFields());

		pane.add(loginBtn, 0, 3);
		pane.add(restoreBtn, 1, 3);

		return pane;
	}

	public void verifyData() {
		String userName = userNameField.getText();
		String password = passwordField.getText();
		clearFields();

		for (User user : getUsersList()) {
			if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
				openTableViewAddDeleteRows();
				closeWindow();
				return;
			}
		}

		// If the username and password don't match, show an alert
		showAlert("Invalid credentials", "The username or password is incorrect.");
	}

	public void openTableViewAddDeleteRows() {
		TableViewAddDeleteRows application = new TableViewAddDeleteRows();
		Stage stage = new Stage();

		try {
			application.start(stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeWindow() {
		Stage stage = (Stage) userNameField.getScene().getWindow();
		stage.close();
	}

	public void clearFields() {
		userNameField.clear();
		passwordField.clear();
	}

	public static List<User> getUsersList() {
		ArrayList<User> users = new ArrayList<>();
		users.add(new User("admin", "admin"));
		users.add(new User("user1", "user1"));
		return users;
	}

	public void showAlert(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
}
