module com.projetobd {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;
    requires java.sql;


    opens com.projetobd to javafx.fxml;
    exports com.projetobd;
    exports com.projetobd.controllers;
    opens com.projetobd.controllers to javafx.fxml;
    opens com.projetobd.models to javafx.base;
}