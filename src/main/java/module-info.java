module ma.emsi.artistapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires poi;
    requires poi.ooxml;


    opens ma.emsi.artistapplication to javafx.fxml;
    exports ma.emsi.artistapplication;
    opens ma.emsi.artistapplication.view to javafx.fxml;
    exports ma.emsi.artistapplication.view;
}