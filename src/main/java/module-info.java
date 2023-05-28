module ma.emsi.artistapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires poi;
    requires poi.ooxml;
	requires com.fasterxml.jackson.databind;
    requires com.opencsv;


    opens ma.emsi.artistapplication to javafx.fxml;
    exports ma.emsi.artistapplication;
    opens ma.emsi.artistapplication.view to javafx.fxml;
    exports ma.emsi.artistapplication.view;
    exports ma.emsi.artistapplication.entities;
}