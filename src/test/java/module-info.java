module Boared.Admin {
    requires com.juanite;
    requires org.junit.jupiter.api;
    requires java.sql;

    opens com.juanite.utils to org.junit.platform.commons;
}