module com.juanite {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.juanite to javafx.fxml;
    exports com.juanite;
    exports com.juanite.model.domain;
    exports com.juanite.controller;
    exports com.juanite.interfaces;
}
