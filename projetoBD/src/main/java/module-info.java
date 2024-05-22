module com.projetobd {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;


    opens com.projetobd to javafx.fxml;
    exports com.projetobd;
    exports com.projetobd.controllers;
    opens com.projetobd.controllers to javafx.fxml;
}