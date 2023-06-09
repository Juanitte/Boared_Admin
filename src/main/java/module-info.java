module com.juanite {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml.bind;
    requires java.sql;
    requires javafx.base;
    requires org.junit.jupiter.api;

    opens com.juanite to javafx.fxml;
    exports com.juanite;
    exports com.juanite.model.domain;
    exports com.juanite.controller;
    exports com.juanite.model.domain.interfaces;
    exports com.juanite.model.DTO;
    opens com.juanite.model.connections to java.xml.bind;
    exports com.juanite.util;
}
