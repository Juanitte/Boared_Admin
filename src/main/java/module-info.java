module com.juanite {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;
    requires java.sql;

    opens com.juanite to javafx.fxml;
    exports com.juanite;
    exports com.juanite.model.domain;
    exports com.juanite.controller;
    exports com.juanite.model.domain.interfaces;
    opens com.juanite.model.connections to java.xml.bind;
}
