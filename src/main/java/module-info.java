module com.lertos.wallsarebad {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.lertos.wallsarebad to javafx.fxml;
    exports com.lertos.wallsarebad;
}