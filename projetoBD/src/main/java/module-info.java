module com.projetobd {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.projetobd to javafx.fxml;
    exports com.projetobd;
    exports Controllers;
    opens Controllers to javafx.fxml;
}